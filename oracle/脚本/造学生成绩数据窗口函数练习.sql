select *
  from (select t.stu_name,
               t.stu_grade,
               t.course_name,
               t.score,
               rank() over(partition by t.course_name,t.stu_grade order by t.score desc) rank_,
               dense_rank() over(order by t.score desc) dense_rank
          from t_student_score t) a where a.dense_rank <=3 and a.stu_grade = 'Èı(1)°à'
