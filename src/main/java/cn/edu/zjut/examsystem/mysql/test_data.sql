
-- 从这里开始下面的东西都是测试内容,不用看,测试数据你可以自己生成





INSERT INTO major(major_name) VALUES ("计算机");
INSERT INTO major(major_name) VALUES ("土木工程");

INSERT INTO review_type(type_name) VALUES ("考试");
INSERT INTO review_type(type_name) VALUES ("考查");

INSERT INTO course(course_name,course_credit,course_preiod,review_type,semester) VALUES
("JAVA",4.0,72,1,"大一上"),
("C++",4.0,72,1,"大一上"),
("流体力学",4.0,72,1,"大一上"),
("土木工程材料",4.0,72,1,"大一上");


INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级JAVA01班",1,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2022级JAVA02班",1,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级C++01班",2,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级C++02班",2,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级流体力学01班",3,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级流体力学02班",3,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级土木工程材料01班",4,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级土木工程材料02班",4,"周一34");

INSERT INTO student(student_name, student_sex, major_num, credit) VALUES 
("小明","男",1,0),
("小红","女",1,0),
("小刚","男",2,0),
("小华","女",1,0),
("小强","男",2,0),
("小丽","女",1,0),
("小杰","男",2,0),
("小梅","女",1,0),
("小军","男",2,0),
("小芳","女",1,0),
("小勇","男",2,0),
("小兰","女",1,0),
("小辉","男",2,0),
("小燕","女",1,0),
("小波","男",2,0),
("小莉","女",1,0),
("小涛","男",2,0),
("小雪","女",1,0),
("小宇","男",2,0),
("小云","女",1,0),
("小飞","男",2,0),
("小琴","女",1,0),
("小龙","男",2,0),
("小凤","女",1,0),
("小成","男",2,0),
("小娟","女",1,0),
("小豪","男",2,0),
("小娜","女",1,0),
("小兵","男",2,0),
("小静","女",1,0),
("小超","男",2,0),
("小薇","女",1,0),
("小伟","男",2,0),
("小蓉","女",1,0),
("小峰","男",2,0),
("小璐","女",1,0),
("小洋","男",2,0),
("小玲","女",1,0),
("小海","男",2,0),
("小玉","女",1,0),
("小林","男",2,0),
("小婷","女",1,0);

INSERT INTO teacher(teacher_name, teacher_sex) VALUES
("张三","男"),
("李四","男"),
("王五","男"),
("赵六","女"),
("本德","男"),
("老王","男"),
("张旭","女"),
("王芳","女");

INSERT INTO class_teacher(class_num, teacher_num) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);


INSERT INTO class_student(class_num, student_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 6), (2, 7), (2, 8), (2, 9), (2, 10),
(3, 11), (3, 12), (3, 13), (3, 14), (3, 15),
(4, 16), (4, 17), (4, 18), (4, 19), (4, 20),
(5, 21), (5, 22), (5, 23), (5, 24), (5, 25),
(6, 26), (6, 27), (6, 28), (6, 29), (6, 30),
(7, 31), (7, 32), (7, 33), (7, 34), (7, 35),
(8, 36), (8, 37), (8, 38), (8, 39), (8, 40),
(1, 41), (1, 42);


INSERT INTO exercise_type(type_name) VALUES
("选择题"),
("主观题"),
("多选题");

-- 题目
INSERT INTO exercise(type_num,course_num,exercise_info,exercise_problem,exercise_answer) VALUES
(1,1,"在Java中，下列哪个关键字用于声明一个类？","A.interface B.class C.struct D.enum","B"),
(1,1,"Java中，哪个类是所有Java类的根类？","A.Object B.Interface C.Class D.String","A"),
(1,1,"在Java中，下列哪个方法用于将字符串转换为整数？","A. Integer.parseInt(String s) B. Integer.toString(int i) C. String.valueOf(int i) D. Integer.valueOf(String s)","A"),
(1,1,"在Java中，下列哪个关键字用于声明一个方法？","A.class B.public C.function D.def","B"),
(1,1,"在Java中，下列哪个是正确的数组初始化方式？","A.int[] myArray = new int[5]; B.int myArray[] = new int[5]; C.Both A and B D. None of the above","C");

INSERT INTO exercise(type_num,course_num,exercise_info,exercise_problem,exercise_answer) VALUES
(1,1,"在Java中，下列哪个关键字用于声明一个类？","A.interface B.class C.struct D.enum","B"),
(1,1,"Java中，哪个类是所有Java类的根类？","A.Object B.Interface C.Class D.String","A"),
(1,1,"在Java中，下列哪个方法用于将字符串转换为整数？","A.Integer.parseInt(String s) B.Integer.toString(int i) C.String.valueOf(int i) D. Integer.valueOf(String s)","A"),
(1,1,"在Java中，下列哪个关键字用于声明一个方法？","A.class B.public C.function D.def","B"),
(1,1,"在Java中，下列哪个是正确的数组初始化方式？","A.int[] myArray = new int[5]; B.int myArray[] = new int[5]; C.Both A and B D.None of the above","C");

INSERT INTO exercise(type_num, course_num, exercise_info, exercise_problem, exercise_answer) VALUES
(1, 2, "C++11中引入了auto关键字，它可以用来自动推断变量的类型。下列哪个声明是正确的？","1. auto x = 10; 2. auto y = 'a'; 3. auto z = 3.14; 4. auto w = x;","1 2 3 4"),
(1, 2, "C++中的模板元编程允许在编译时进行计算。下列哪个代码片段是模板元编程的正确示例？","1. template<int N> struct Factorial; 2. template<> struct Factorial<0> { enum { value = 1 }; }; 3. template<int N> struct Factorial { enum { value = N * Factorial<N-1>::value }; }; 4. template<int N> int factorial() { return N <= 1 ? 1 : N * factorial<N-1>(); }","1 2 3"),
(1, 2, "C++中的lambda表达式提供了一种编写匿名函数的方法。下列哪个lambda表达式是正确的？","1. [&] (int x) { return x * x; }; 2. [=] (int x) { return x + 10; }; 3. [] (int x, int y) { return x + y; }; 4. [a] (int x) { return x + a; };","1 2 3 4"),
(1, 2, "C++中的智能指针用于自动管理动态分配的内存。下列哪个智能指针不能释放其管理的内存？","1. std::unique_ptr 2. std::shared_ptr 3. std::weak_ptr 4. std::auto_ptr","3"),
(1, 2, "C++中的多态性允许通过基类指针调用派生类的函数。下列哪个代码片段正确地展示了多态性？","1. class Base { virtual void func() {} }; 2. class Derived : public Base { void func() override {} }; 3. Base* b = new Derived(); 4. b->func();","1 2 3 4");




-- 样卷
INSERT INTO exam(course_num,teacher_num,is_released,total_score) VALUES
(1,1,1,100);

INSERT INTO exam_module(exam_num,exercise_type,module_in_exam_num) VALUES
(1,1,1);

INSERT INTO module_exercise(exercise_num,module_num,exercise_in_module_num,max_score) VALUES
(1,1,1,5),
(2,1,2,5),
(3,1,3,5),
(4,1,4,5),
(5,1,5,5);



INSERT INTO exam_type(type_name) VALUES
("期末考试"),
("期中考试"),
("平时考试");


INSERT INTO exam_scheme(exam_num,scheme_begin,scheme_end,course_num,exam_type,class_num,teacher_num,exam_review_model,ended) VALUES
(1,"2022-12-2-13:30","2022-12-2-15:30",1,1,"2","2,3,4",1,false);


INSERT INTO answer_sheet_status_type(type_name) VALUES
("待批阅"),
("已批阅"),
("作弊者"),
("异常");


INSERT INTO answer_sheet(scheme_num,student_id,sheet_status) VALUES
(1,1,1),
(1,2,1);


INSERT INTO answer_sheet_detail(sheet_num,module_exercise_id,answer) VALUES
(1,1,"B"),
(1,2,"C"),
(1,3,"A"),
(1,4,"D"),
(1,5,"B");


INSERT INTO answer_sheet_detail(sheet_num,module_exercise_id,answer) VALUES
(2,1,"B"),
(2,2,"C"),
(2,3,"A"),
(2,4,"D"),
(2,5,"B");


INSERT INTO student_account(username,password_,student_id) VALUES
("小明","123456",1),
("大明","123456",2),
("中明","123456",3),
("微明","123456",4);

INSERT INTO teacher_account(username,password_,teacher_num) VALUES
("sensei01","123456",1),
("sensei02","123456",2),
("sensei03","123456",3),
("sensei04","123456",4);

INSERT INTO leader_account(username,password_) VALUES
("sensei01","123456"),
("sensei02","123456"),
("sensei03","123456"),
("sensei04","123456");




INSERT INTO course_teacher(course_num, teacher_num) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(1, 5),
(2, 6),
(3, 7),
(4, 8);


INSERT INTO course_major(course_num, major_num) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2);


insert into exam_review_model(model_name) VALUES
("老师-题目"),
("老师-答题表");


select
        pas1_0.sheet_num,
        asd1_0.sheet_num,
        asd1_0.detail_id,
        asd1_0.answer,
        me1_0.module_exercise_id,
        me1_0.exercise_num,
        me1_0.exercise_in_module_num,
        me1_0.module_num,
        asd1_0.score,
        pas1_0.scheme_num,
        pas1_0.sheet_status,
        pas1_0.student_id,
        pas1_0.total_score 
    from
        answer_sheet pas1_0 
    join
        answer_sheet_detail asd1_0 
            on pas1_0.sheet_num=asd1_0.sheet_num 
    join
        module_exercise me1_0 
            on me1_0.module_exercise_id=asd1_0.module_exercise_id 
    where
        pas1_0.sheet_num=2
        and pas1_0.student_id=2
    order by
        me1_0.module_num,
        me1_0.exercise_in_module_num;