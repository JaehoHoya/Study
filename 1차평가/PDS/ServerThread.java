package PDS;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerThread extends  Thread{

    private Socket s;

    public ServerThread(Socket s) {
        this.s = s;
    }


    // try catch - oin ,oos 받기 여기서

    @Override
    public void run() {

        try
        {
            OutputStream out=s.getOutputStream();
            ObjectOutputStream oos= new ObjectOutputStream(out);
            InputStream in=s.getInputStream();
            ObjectInputStream oin=new ObjectInputStream(in);



            while (true) {


                PDSResponse resMenu= new PDSResponse();
                resMenu.Menu= "파일 업로드(u), 목록보기(s), 상세보기(i), 제목으로 검색(f), 수정(m), 삭제(d), 종료(x)";
                oos.writeObject(resMenu);
                oos.flush();

                PDSRequest request = (PDSRequest) oin.readObject();

                if (request.upload) {
                    if (request.fdata.length > 0) {
                        boolean saved = new PDSFileIO().save(request.fname, request.fdata);

                        if (saved) {
                            List<PDSVO> file = new PDSFileIO().deserialization();

                            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                            String date= sdf.format(new Date());

                            PDSVO data = new PDSVO(getNextFileNumber(file), request.fname, request.fdata, request.author,date, request.desc);
                            file.add(data);
                            boolean saved2 = new PDSFileIO().serialization(file);
                            PDSResponse res = new PDSResponse();
                            if (saved2) {
                                res.response = "파일 업로드 성공\n";
                                oos.writeObject(res);
                                oos.flush();
                            } else {
                                res.response = "파일 업로드 실패\n";
                                oos.writeObject(res);
                                oos.flush();
                            }


                        }
                    }
                }
                else if (request.showFiles)
                {
                    List<PDSVO> file = new PDSFileIO().deserialization();
                    PDSResponse response = new PDSResponse();
                    response.list = file;
                    oos.writeObject(response);
                    oos.flush();
                }
                else if (request.viewDetails)
                {
                    List<PDSVO> file = new PDSFileIO().deserialization();
                    PDSResponse res = new PDSResponse();
                    res.list = file;
                    oos.writeObject(res);
                    oos.flush();
                }
                else if (request.search)
                {
                    List<PDSVO> file = new PDSFileIO().deserialization();
                    PDSVO key = new PDSVO(request.fname);
                    if (file.contains(key)) {
                        int idx = file.indexOf(key);

                        PDSResponse res = new PDSResponse();
                        res.list=new ArrayList<PDSVO>();
                        res.list.add(file.get(idx));
                        oos.writeObject(res);
                        oos.flush();

                    } else {
                        PDSResponse res = new PDSResponse();
                        res.response = "파일이 존재하지 않습니다\n";
                        oos.writeObject(res);
                        oos.flush();
                    }


                } else if (request.update) {
                    List<PDSVO> file = new PDSFileIO().deserialization();
                    PDSVO key = new PDSVO(request.fname);
                    if (file.contains(key)) {
                        int idx = file.indexOf(key);
                        file.get(idx).setDesc(request.desc);
                        boolean saved = new PDSFileIO().serialization(file);
                        PDSResponse res = new PDSResponse();

                        if (saved) {
                            res.response = "수정 완료\n";
                            oos.writeObject(res);
                            oos.flush();
                        } else {
                            res.response = "변경 실패\n";
                            oos.writeObject(res);
                            oos.flush();
                        }
                    } else {
                        PDSResponse res = new PDSResponse();
                        res.response = "존재하는 파일 번호가 아닙니다.\n";
                        oos.writeObject(res);
                        oos.flush();
                    }

                }
                else if (request.delete)
                {

                    List<PDSVO> file = new PDSFileIO().deserialization();
                    PDSVO key = new PDSVO(request.no);
                    if (file.contains(key))
                    {
                        int idx = file.indexOf(key);

                        boolean deleteFile = new PDSFileIO().deleteFile(file.get(idx).getFname());
                        file.remove(idx);


                        for (int i = 0; i < file.size(); i++) {
                            file.get(i).setNo(i + 1);
                        }





                        boolean saved = new PDSFileIO().serialization(file);
                        PDSResponse res= new PDSResponse();
                        if(deleteFile && saved)
                        {

                            res.response="파일 삭제 완료\n";
                        }
                        else {res.response="파일 삭제 오류\n";
                        }
                        oos.writeObject(res);
                        oos.flush();

                    }

                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    private int getNextFileNumber(List<PDSVO> files) {
        int maxNumber = 0;
        for (PDSVO file : files) {
            if (file.getNo() > maxNumber) {
                maxNumber = file.getNo();
            }
        }
        return maxNumber + 1;
    }
}

