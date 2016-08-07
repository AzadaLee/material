
create or replace type arr_stunames is varray(15)of varchar2(30);--定义一个学生姓名数组 
create or replace type arr_score is varray(5) of number(4);

create or replace procedure pro_score(stuGrade in varchar2) is
  arrStuNames arr_stunames := arr_stunames('Doug Lee','Rod Json','Larry Son','TaoXm','MaY',
                                           'LiYh','YuMh','LeiJ','MaHt','Steve Jobs',
                                           'Bill Gates','ZhouHy','RenZhf','HuangJx','Tom Preston-Werner'
                                           );
  arrScores arr_score := arr_score(95,96,97,98,99);
 -- cursor cur_student is select * from t_student;
  --student t_student%rowtype;
  cursor cur_course is select * from t_course;
  course cur_course%rowtype;
  cIndex number :=1;--用于取分数
  begin
    for i in 1.. arrStuNames.count
      loop
        dbms_output.put_line('current student name is '|| arrStuNames(i));
        cIndex := mod(i,5)+1;
        open cur_course;
          loop
            fetch cur_course into course;
            exit when cur_course%notfound;
            insert into t_student_score(id,stu_name,stu_grade,course_name,score,score_date)
                   values(sys_guid(),arrStuNames(i),stuGrade,course.course_name,arrScores(cIndex),sysdate);
          end loop;
        close cur_course;
      end loop;
      commit;
  end;
