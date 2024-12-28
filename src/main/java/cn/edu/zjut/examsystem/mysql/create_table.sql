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
is_released boolean,
total_score INT

-- FOREIGN KEY (course_num) REFERENCES course(course_num),
-- FOREIGN KEY (teacher_num) REFERENCES teacher(teacher_num)
);

DROP TABLE 	IF EXISTS exam_review_model;
CREATE TABLE exam_review_model
(
model_num INT AUTO_INCREMENT PRIMARY KEY,
model_name VARCHAR(30)
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
exercise_in_module_num INT,
max_score INT

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
teacher_num VARCHAR(3000),
exam_review_model INT,
ended BOOLEAN,
settlemented BOOLEAN	-- 判断是否结算学分完毕了

-- FOREIGN KEY (exam_num) REFERENCES exam(exam_num),
-- FOREIGN KEY (course_num) REFERENCES course(course_num),
-- FOREIGN KEY (exam_type) REFERENCES exam_type(type_num)
);


CREATE INDEX idx_scheme_end_ended ON exam_scheme(scheme_end,ended);
CREATE INDEX idx_exam_type_settlemented ON exam_scheme(exam_type,settlemented);


DROP TABLE 	IF EXISTS task_list_teacher_exercise_model;
CREATE TABLE task_list_teacher_exercise_model
(
id INT AUTO_INCREMENT PRIMARY KEY,	-- 为了给jpa接管做的id,没有实际含义
teacher_num INT,
model_exercise_id INT
);


DROP TABLE 	IF EXISTS task_list_teacher_answer_sheet_model;
CREATE TABLE task_list_teacher_answer_sheet_model
(
id INT AUTO_INCREMENT PRIMARY KEY,	-- 为了给jpa接管做的id,没有实际含义
teacher_num INT,
answer_sheet_num INT
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





DROP FUNCTION IF EXISTS SumScoresBySheetNum;

DELIMITER $$

CREATE FUNCTION SumScoresBySheetNum(sheetNum INT) RETURNS INT
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

