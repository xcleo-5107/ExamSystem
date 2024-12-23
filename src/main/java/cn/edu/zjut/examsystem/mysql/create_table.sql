DROP TABLE IF EXISTS major;
CREATE TABLE major
(
major_name VARCHAR(60),
major_num INT AUTO_INCREMENT PRIMARY KEY
);




DROP TABLE 	IF EXISTS review_type;
CREATE TABLE review_type
(
type_num INT AUTO_INCREMENT PRIMARY KEY,
type_name VARCHAR(60)
);




DROP TABLE IF EXISTS course;
CREATE TABLE course
(
course_name VARCHAR(60),
course_num INT AUTO_INCREMENT PRIMARY KEY,
course_credit FLOAT(10,1) NOT NULL,
course_preiod int,
review_type INT,
major_num INT,
semester VARCHAR(60)

-- FOREIGN KEY (review_type) REFERENCES review_type(type_num),
-- FOREIGN KEY (major_num) REFERENCES major(major_num)
);


DROP TABLE IF EXISTS course_marjor;
CREATE TABLE course_marjor
(
major_num INT,
course_num INT

-- FOREIGN KEY (major_num) REFERENCES major(major_num),
-- FOREIGN KEY (course_num) REFERENCES course(course_num)
);


DROP TABLE IF EXISTS class;
CREATE TABLE class
(
class_name VARCHAR(60),
class_num INT AUTO_INCREMENT PRIMARY KEY,
course_num INT,
class_time VARCHAR(120)

-- FOREIGN KEY (course_num) REFERENCES course(course_num)
);


DROP TABLE IF EXISTS student;
CREATE TABLE student
(
student_name VARCHAR(60),
student_id INT AUTO_INCREMENT PRIMARY KEY,
student_sex VARCHAR(10),
major_num INT,
credit float(10,1),
semester VARCHAR(60)

-- FOREIGN KEY (major_num) REFERENCES major(major_num)
);

DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher
(
teacher_name VARCHAR(60),
teacher_num INT AUTO_INCREMENT PRIMARY KEY,
teacher_sex VARCHAR(10)
);



DROP TABLE IF EXISTS course_teacher;
CREATE TABLE course_teacher
(
course_num INT,
teacher_num INT

-- FOREIGN KEY (course_num) REFERENCES course(course_num),
-- FOREIGN KEY (teacher_num) REFERENCES teacher(teacher_num)
);


DROP TABLE IF EXISTS class_teacher;
CREATE TABLE class_teacher
(
class_num INT,
teacher_num INT

-- FOREIGN KEY (class_num) REFERENCES class(class_num),
-- FOREIGN KEY (teacher_num) REFERENCES teacher(teacher_num)
);



DROP TABLE IF EXISTS class_student;
CREATE TABLE class_student
(
class_num INT,
student_id INT

-- FOREIGN KEY (class_num) REFERENCES class(class_num),
-- FOREIGN KEY (student_id) REFERENCES student(student_id)
);



DROP TABLE 	IF EXISTS exercise_type;
CREATE TABLE exercise_type
(
type_num INT AUTO_INCREMENT PRIMARY KEY,
type_name VARCHAR(60)
);



DROP TABLE 	IF EXISTS exercise;
CREATE TABLE exercise
(
exercise_num INT AUTO_INCREMENT PRIMARY KEY,
type_num INT,
course_num INT,
exercise_info VARCHAR(3000),
exercise_problem VARCHAR(3000),
exercise_answer VARCHAR(3000),
exercise_other BLOB,
exercise_difficulty INT

-- FOREIGN KEY (type_num) REFERENCES exercise_type(type_num),
-- FOREIGN KEY (course_num) REFERENCES course(course_num)
);



DROP TABLE 	IF EXISTS exam;
CREATE TABLE exam
(
exam_num INT AUTO_INCREMENT PRIMARY KEY,
course_num INT,
teacher_num INT,
is_released boolean

-- FOREIGN KEY (course_num) REFERENCES course(course_num),
-- FOREIGN KEY (teacher_num) REFERENCES teacher(teacher_num)
);


DROP TABLE 	IF EXISTS exam_module;
CREATE TABLE exam_module
(
exam_num INT,
exercise_type INT,
module_num INT AUTO_INCREMENT PRIMARY KEY,
module_in_exam_num INT

-- FOREIGN KEY (exam_num) REFERENCES exam(exam_num),
-- FOREIGN KEY (exercise_type) REFERENCES exercise_type(type_num)
);



DROP TABLE 	IF EXISTS module_exercise;
CREATE TABLE module_exercise
(
module_exercise_id INT AUTO_INCREMENT PRIMARY KEY,
exercise_num INT,
module_num INT,
exercise_in_module_num INT

-- FOREIGN KEY (exercise_num) REFERENCES exercise(exercise_num),
-- FOREIGN KEY (module_num) REFERENCES exam_module(module_num)
);


DROP TABLE 	IF EXISTS exam_type;
CREATE TABLE exam_type
(
type_num INT AUTO_INCREMENT PRIMARY KEY,
type_name VARCHAR(60)
);



DROP TABLE 	IF EXISTS exam_scheme;
CREATE TABLE exam_scheme
(
scheme_num INT AUTO_INCREMENT PRIMARY KEY,
exam_num INT,
scheme_begin DATETIME,
scheme_end DATETIME,
course_num INT,
exam_type INT,
class_num VARCHAR(3000),
teacher_num VARCHAR(3000)

-- FOREIGN KEY (exam_num) REFERENCES exam(exam_num),
-- FOREIGN KEY (course_num) REFERENCES course(course_num),
-- FOREIGN KEY (exam_type) REFERENCES exam_type(type_num)
);


DROP TABLE 	IF EXISTS answer_sheet_status_type;
CREATE TABLE answer_sheet_status_type
(
type_num INT AUTO_INCREMENT PRIMARY KEY,
type_name VARCHAR(60)
);



DROP TABLE 	IF EXISTS answer_sheet;
CREATE TABLE answer_sheet
(
sheet_num INT AUTO_INCREMENT PRIMARY KEY,
scheme_num INT,
student_id INT,
total_score INT DEFAULT 0,
sheet_status INT

-- FOREIGN KEY (scheme_num) REFERENCES exam_scheme(scheme_num),
-- FOREIGN KEY (student_id) REFERENCES student(student_id),
-- FOREIGN KEY (sheet_status) REFERENCES answer_sheet_status_type(type_num)
);




DROP TABLE 	IF EXISTS answer_sheet_detail;
CREATE TABLE answer_sheet_detail
(
detail_id INT AUTO_INCREMENT PRIMARY KEY,
sheet_num INT,
module_exercise_id INT,
answer VARCHAR(3000),
score INT

-- FOREIGN KEY (sheet_num) REFERENCES answer_sheet(sheet_num)
);





DROP TABLE 	IF EXISTS student_account;
CREATE TABLE student_account
(
account_num INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(60),
password_ VARCHAR(60) NOT NULL,
student_id INT

-- FOREIGN KEY (student_id) REFERENCES student(student_id)
);




DROP TABLE 	IF EXISTS teacher_account;
CREATE TABLE teacher_account
(
account_num INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(60),
password_ VARCHAR(60) NOT NULL,
teacher_num INT

-- FOREIGN KEY (teacher_num) REFERENCES teacher(teacher_num)
);




DROP TABLE 	IF EXISTS leader_account;
CREATE TABLE leader_account
(
account_num INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(60),
password_ VARCHAR(60) NOT NULL
);

-- 从这里开始下面的东西都是测试内容,不用看,测试数据你可以自己生成





INSERT INTO major(major_name) VALUES ("计算机");
INSERT INTO major(major_name) VALUES ("土木工程");

INSERT INTO review_type(type_name) VALUES ("考试");
INSERT INTO review_type(type_name) VALUES ("考查");

INSERT INTO course(course_name,course_credit,course_preiod,review_type,major_num,semester) VALUES
("JAVA",4.0,72,1,1,"大一上"),
("C++",4.0,72,1,1,"大一上"),
("流体力学",4.0,72,1,2,"大一上"),
("土木工程材料",4.0,72,1,2,"大一上");


INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级JAVA01班",1,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2022级JAVA02班",1,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级C++01班",2,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级C++02班",2,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级流体力学01班",3,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级流体力学02班",3,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级土木工程材料01班",4,"周一34");
INSERT INTO class(class_name,course_num,class_time) VALUES ("2021级土木工程材料02班",4,"周一34");

INSERT INTO student(student_name, student_sex, major_num) VALUES 
("小明","男",1),
("小红","女",1),
("小刚","男",2),
("小华","女",1),
("小强","男",2),
("小丽","女",1),
("小杰","男",2),
("小梅","女",1),
("小军","男",2),
("小芳","女",1),
("小勇","男",2),
("小兰","女",1),
("小辉","男",2),
("小燕","女",1),
("小波","男",2),
("小莉","女",1),
("小涛","男",2),
("小雪","女",1),
("小宇","男",2),
("小云","女",1),
("小飞","男",2),
("小琴","女",1),
("小龙","男",2),
("小凤","女",1),
("小成","男",2),
("小娟","女",1),
("小豪","男",2),
("小娜","女",1),
("小兵","男",2),
("小静","女",1),
("小超","男",2),
("小薇","女",1),
("小伟","男",2),
("小蓉","女",1),
("小峰","男",2),
("小璐","女",1),
("小洋","男",2),
("小玲","女",1),
("小海","男",2),
("小玉","女",1),
("小林","男",2),
("小婷","女",1);

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
("听力选择题");

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
INSERT INTO exam(course_num,teacher_num,is_released) VALUES
(1,1,1);

INSERT INTO exam_module(exam_num,exercise_type,module_in_exam_num) VALUES
(1,1,1);

INSERT INTO module_exercise(exercise_num,module_num,exercise_in_module_num) VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5);



INSERT INTO exam_type(type_name) VALUES
("期末考试"),
("期中考试"),
("平时考试");


INSERT INTO exam_scheme(exam_num,scheme_begin,scheme_end,course_num,exam_type) VALUES
(1,"2022-12-2-13:30","2022-12-2-15:30",1,1);


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











-- 存储过程(已弃用)

DROP PROCEDURE IF EXISTS GetCourseByStudentId;
DELIMITER $$

CREATE PROCEDURE GetCourseByStudentId(IN id VARCHAR(10))
BEGIN
    SELECT 
        c.course_name AS "可选课程名",
				c.course_num AS "课程编号"
    FROM 
        courses c
    WHERE c.major_num = (
		SELECT major_num
		FROM students
		WHERE student_id = id
		);
END$$

DELIMITER ;

CALL GetCourseByStudentId("S01");



DROP PROCEDURE IF EXISTS GetAllTeacherInClass;
DELIMITER $$

CREATE PROCEDURE GetAllTeacherInClass()
BEGIN
    SELECT 
        t.teacher_name AS "教师名",
				cl.class_name AS "班级名",
				cl.course_num AS "班级课程编号",
				co.course_name AS "课程名称"
    FROM 
        teachers t
				JOIN classes_teachers AS ct ON t.teacher_num = ct.teacher_num
				JOIN classes AS cl ON ct.class_num = cl.class_num
				JOIN courses AS co ON co.course_num = cl.course_num;
END$$

DELIMITER ;

CALL GetAllTeacherInClass();





DROP PROCEDURE IF EXISTS ClassSheet;
DELIMITER $$

CREATE PROCEDURE ClassSheet(IN classNum VARCHAR(10))
BEGIN
    SELECT 
        s.student_name AS "学生名",
				cl.class_name AS "班级名",
				cl.course_num AS "班级课程编号",
				co.course_name AS "课程名称"
    FROM 
        students s
				JOIN classes_students AS cs ON s.student_id = cs.student_id
				JOIN classes AS cl ON cs.class_num = cl.class_num
				JOIN courses AS co ON co.course_num = cl.course_num
		WHERE
				cs.class_num = classNum;
END$$

DELIMITER ;

CALL ClassSheet("C01");





-- 根据答题表编号查询答题表详情
DROP PROCEDURE IF EXISTS GetAnswerSheetBySheetId;
DELIMITER $$

CREATE PROCEDURE GetAnswerSheetBySheetId(IN sheetId INT)
BEGIN
    SELECT  
        e.exercise_info "题干",
				e.exercise_problem "问题",
				e.exercise_answer	"参考答案",
				et.type_name AS "题目类型",
				asd.exercise_in_module_num AS "小题号",
				asd.answer AS "考生答案",
				asd.score AS "得分"
    FROM 
        answer_sheet_detail asd
				JOIN modules_exercises AS me ON me.module_num = asd.module_num AND me.exercise_in_module_num = asd.exercise_in_module_num
				JOIN exercises AS e ON e.exercise_num = me.exercise_num
				JOIN exercise_type AS et ON e.type_num = et.type_num
		WHERE
				asd.sheet_num = sheetId
		ORDER BY
				asd.module_num ASC,
				asd.exercise_in_module_num ASC;
END$$

DELIMITER ;

CALL GetAnswerSheetBySheetId(4);





DROP FUNCTION IF EXISTS SumScoresByNum;

DELIMITER $$

CREATE FUNCTION SumScoresByNum(sheetNum INT) RETURNS INT
    DETERMINISTIC
    READS SQL DATA
BEGIN
    DECLARE totalScore INT DEFAULT 0;
    SELECT SUM(score) INTO totalScore
    FROM answer_sheet_detail
    WHERE sheet_num = sheetNum;
    RETURN totalScore;
END$$

DELIMITER ;






DROP PROCEDURE IF EXISTS AutoAlterTotalScore;
DELIMITER $$

CREATE PROCEDURE AutoAlterTotalScore(IN sheetNum INT)
BEGIN
    DECLARE examType VARCHAR(10);
		DECLARE old_total_score INT DEFAULT 0;
		DECLARE courseCredit FLOAT(10,1) DEFAULT 0.0;
		DECLARE studentId VARCHAR(10);
		DECLARE new_total_score INT;
		
		DECLARE test float(10,1);
		
    SELECT type_name INTO examType
    FROM answer_sheet AS a
    JOIN exam_scheme AS es ON a.scheme_num = es.scheme_num
    JOIN exam_type AS et ON et.type_num = es.exam_type
    WHERE a.sheet_num = sheetNum;
    
    SELECT total_score INTO old_total_score
    FROM answer_sheet
    WHERE sheet_num = sheetNum;
    
    SET new_total_score = SumScoresByNum(sheetNum);
    
    
    SELECT c.course_credit INTO courseCredit
    FROM answer_sheet AS a
    JOIN exam_scheme AS es ON es.scheme_num = a.scheme_num
		JOIN courses AS c ON c.course_num = es.course_num
    WHERE a.sheet_num = sheetNum;
    
    SELECT student_id INTO studentId
    FROM answer_sheet
    WHERE sheet_num = sheetNum;

    UPDATE answer_sheet
    SET total_score = new_total_score
    WHERE sheet_num = sheetNum;
		
		SET test = (new_total_score - old_total_score) / 100 * courseCredit;
    
    IF examType = '期末考试' THEN
        UPDATE students
        SET credit = credit + test
        WHERE student_id = studentId;
    END IF;
END$$

DELIMITER ;



CALL AutoAlterTotalScore(2);





-- 查询学生账号
SELECT *
FROM student_account;

-- 查询学生信息
SELECT *
FROM students
WHERE student_id = "S01";

-- 查询课程表
SELECT co.course_name,cla.class_time
FROM classes_students AS cs
JOIN classes AS cla ON cs.class_num = cla.class_num
JOIN courses AS co ON cla.course_num = co.course_num
WHERE student_id = "S01"


-- 查询考试安排
SELECT scheme_begin,scheme_end,course_name
FROM exam_scheme AS es
JOIN courses AS c ON es.course_num = c.course_num
WHERE es.class_num LIKE CONCAT("%",(SELECT class_num FROM classes_students WHERE student_id = "S01"),"%") AND es.scheme_end>CURRENT_TIME AND es.scheme_begin<CURRENT_TIME;


-- 查询考试对应的试卷
SELECT em.module_in_exam_num,me.exercise_in_module_num,e.exercise_info,e.exercise_problem,e.exercise_other
FROM exams_modules AS em
JOIN modules_exercises AS me ON em.module_num = me.module_num
JOIN exercises AS e ON me.exercise_num = e.exercise_num
WHERE exam_num = (SELECT exam_num FROM exam_scheme WHERE scheme_num=3)
ORDER BY 
	em.module_in_exam_num ASC,
	me.exercise_in_module_num ASC;
	
	
	
	

-- 查询已经考完的成绩	
SELECT scheme_begin,scheme_end,course_name,ans.total_score
FROM exam_scheme AS es
JOIN courses AS c ON es.course_num = c.course_num
JOIN answer_sheet AS ans ON ans.scheme_num = es.scheme_num
JOIN answer_sheet_status_type AS asst on asst.type_num = ans.sheet_status
WHERE es.class_num LIKE CONCAT("%",(SELECT class_num FROM classes_students WHERE student_id = "S01"),"%") AND es.scheme_end<CURRENT_TIME AND ans.student_id = "S01" AND asst.type_name !="待批阅";



-- 查询可选课程
SELECT c.course_name,
c.course_credit,
c.course_preiod,
rt.type_name AS "考察类型"
FROM courses AS c
JOIN review_type AS rt ON c.review_type = rt.type_num
WHERE major_num = (SELECT major_num FROM students WHERE student_id = "S01") AND c.semester = (SELECT semester FROM students WHERE student_id = "S01");



SELECT *
FROM teacher_account;


SELECT *
FROM teachers
WHERE teacher_num = "T01";


-- 查询该教师所教课程的题目
SELECT 
e.exercise_num,
et.type_name AS "题型",
c.course_name,
e.exercise_info,
e.exercise_problem,
e.exercise_answer,
e.exercise_other
FROM exercises AS e
JOIN exercise_type AS et ON e.type_num = et.type_num
JOIN courses AS c ON e.course_num = c.course_num
WHERE e.course_num = (SELECT course_num FROM courses_teachers WHERE teacher_num="T01");



-- 查询所有试卷
SELECT e.exam_num,c.course_num,c.course_name
FROM exams AS e
JOIN courses AS c ON e.course_num = c.course_num;



-- 根据试卷编号查询试卷详情
DROP PROCEDURE IF EXISTS GetExamById;
DELIMITER $$

CREATE PROCEDURE GetExamById(IN examId INT)
BEGIN
    SELECT  
        ex.exercise_info AS "题干",
				ex.exercise_problem AS "问题",
				ex.exercise_answer AS	"参考答案",
				c.course_name	AS "所属课程",
				et.type_name AS "题目类型",
				em.module_in_exam_num AS "大题号",
				me.exercise_in_module_num AS "小题号"
    FROM 
        exams AS e
				JOIN courses AS c ON e.course_num = c.course_num
				JOIN exams_modules AS em ON em.exam_num = e.exam_num
				JOIN modules_exercises AS me ON me.module_num = em.module_num
				JOIN exercises AS ex ON ex.exercise_num = me.exercise_num
				JOIN exercise_type AS et ON ex.type_num = et.type_num
		WHERE
				e.exam_num = examId
		ORDER BY
				em.module_in_exam_num ASC,
				me.exercise_in_module_num ASC;
END$$

DELIMITER ;

CALL GetExamById(1);




-- 查询考试安排
SELECT *
FROM exam_scheme
JOIN courses ON exam_scheme.course_num=courses.course_num
JOIN exam_type ON exam_type.type_num = exam_scheme.exam_type;



-- 查询参与考试的班级
SELECT *
FROM classes
WHERE class_num="C01";


-- 查询参与考试的班级的人员名单
SELECT s.student_name,s.student_id
FROM classes_students AS cs
JOIN students AS s ON s.student_id = cs.student_id
WHERE cs.class_num = "C01";



-- 查询还需要批阅的考试场次
SELECT *
FROM exam_scheme
JOIN courses ON exam_scheme.course_num=courses.course_num
JOIN exam_type ON exam_type.type_num = exam_scheme.exam_type
WHERE EXISTS
(SELECT * 
FROM answer_sheet AS ans
JOIN answer_sheet_status_type AS asst ON ans.sheet_status = asst.type_num
WHERE scheme_num=3 AND asst.type_name="待批阅");



SELECT *
FROM classes AS cla
JOIN classes_teachers AS ct ON cla.class_num = ct.class_num
JOIN courses AS co ON cla.course_num=co.course_num
WHERE ct.teacher_num="T01";


SELECT s.student_id,s.student_name
FROM classes AS cla
JOIN classes_students AS cs ON cla.class_num = cs.class_num
JOIN students AS s ON cs.student_id = s.student_id
WHERE cla.class_num="C01";



SELECT *
FROM course
WHERE course_name LIKE "%A%";


select
        es.scheme_num,
        es.scheme_begin,
        es.scheme_end,
        es.class_num,
        es.teacher_num,
        es.exam_num,
        es.exam_type,
        c.course_num 
    from
        exam_scheme AS es 
    JOIN
        exam_type AS et 
            On et.type_num = es.exam_type 
    JOIN
        course AS c 
            ON c.course_num = es.course_num 
    WHERE DATE_FORMAT(es.scheme_end, '%Y-%m-%d') LIKE "%2022%";