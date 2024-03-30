
CREATE TABLE public.administrators (
    phone character varying(255),
    user_id SERIAL PRIMARY KEY
);



CREATE TABLE public.answer (
    id Bigint PRIMARY KEY,
    answer_name text,
    is_correct_answer boolean
);


CREATE SEQUENCE public.answer_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.messages (
    message_id bigint PRIMARY KEY,
    message character varying(255),
    "timestamp" timestamp without time zone
);


CREATE SEQUENCE public.messages_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.messages_sender_receiver (
    id bigint PRIMARY KEY,
    receiver_id integer,
    sender_id integer,
    message_message_id bigint
);


CREATE SEQUENCE public.messages_sender_receiver_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.question (
    q_id bigint PRIMARY KEY,
    question_name character varying(255) NOT NULL
);


CREATE TABLE public.question_answer (
    id bigint PRIMARY KEY,
    answer_id bigint,
    is_correct_answer boolean NOT NULL,
    question_id bigint
);


CREATE SEQUENCE public.question_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE public.question_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.result (
    result_id bigint PRIMARY KEY,
    result_name character varying(255),
    score integer,
    "timestamp" timestamp without time zone,
    student_user_id integer
);



CREATE SEQUENCE public.result_result_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.roles (
    role_id  PRIMARY KEY,
    role_name character varying(255)
);



CREATE TABLE public.students (
    first_name character varying(255),
    second_name character varying(255),
    user_id  SERIAL PRIMARY KEY,
    tutor_user_id integer
);


CREATE TABLE public.tutor (
    first_name character varying(255),
    phone character varying(255),
    second_name character varying(255),
    user_id  SERIAL PRIMARY KEY
);




CREATE TABLE public.users (
    user_id SERIAL PRIMARY KEY,
    email character varying(255),
    password character varying(255),
    username character varying(255),
    fk_role_id integer
);



CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;






INSERT INTO public.roles(
	role_id, role_name)
	VALUES (1, 'STUDENT'),
	(2, 'TUTOR'),
	(3, 'ADMIN');


INSERT INTO public.users(
	user_id, email, password, username, fk_role_id)
	VALUES (1, 'bob@gmail.com', '$2a$10$kT2qymnSAY16mWF34D77.u7VGfOppRTlVmXXIDEDq.GaVw6.h3Wvm', 'bob', 1),
	(2, 'harry@gmail.com', '$2a$10$wyKZC81CKakoP.9HOlIqKOF6RHr/uNYiHhnMfay1fKp/Gw..qNK6q', 'harry', 1),
	(3, 'liza@gmail.com', '$2a$10$Pbve2pkoGtlZL00DuTPmoemJqqRNxPUg6C2yLk2baTCE4Cx9wZCWq', 'liza', 2),
	(4, 'sam@gmail.com', '$2a$10$d55/YTV3wRu6fcM3RN0ffeGRiVKYbbqRdolY.nmqCG2VFnMXXaR/O', 'sam', 1),
	(5, 'admin@gmail.com', '$2a$10$h4/M/gVmCsDzpI3VUN0RHewpX.TfSG0omQ7Ys30yvVbM8vNYqcJQ6', 'admin', 3),
	(6, 'madison@gmail.com', '$2a$10$92xceUaLtypHvTYmceAzRuGhT9mAhh.yJgIQvvEIwLmuB83VlPOWe', 'madison', 2),
	(7, 'tom@gmail.com', '$2a$10$DHuipAl7Zq2Pr/chbz.x9.y6BnrjQ8MGweli7xpuh5jTlKG9h/qO2', 'tom', 1);

INSERT INTO public.tutor(
	first_name, phone, second_name, user_id)
	VALUES ('Elizabeth', '88005353535', 'Arterton', 3),
	('Madison', '0254652155', 'Jonhson', 6);

 INSERT INTO public.students(
	first_name, second_name, user_id, tutor_user_id)
	VALUES ('Bob', 'Marley', 1, 6),
	('Harry', 'Potter', 2, 3),
	('Sam', 'Serious', 4, 6),
	('Thomas', 'Edison', 7, 3);

INSERT INTO public.administrators(
	phone, user_id)
	VALUES ('88008008008', 5);

INSERT INTO public.question(
	q_id, question_name)
	VALUES (1, 'I can not find my keys. I do not know where ____ are.'),
	(2, 'Choose a correct plural from of the word  “Man”.'),
	(3, 'Choose a correct plural from of the word “Person”.'),
	(4, 'When ____?'),
	(5, 'They threw a rock _____ the window and broke the glass.'),
	(6, 'I did not see _____ strange when I went into the room.'),
	(7, 'A: "It is really hot in this room." B: "Wait. I _____ the window."'),
	(8, 'I _____ the tickets on Friday.'),
	(9, 'I _____ in this village all my life.'),
	(10,'I wish I _____ those words. But now it is too late.'),
	(11,'The woman, who has been missing for 10 days, is believed _____.'),
	(12,'She was working on her computer with her baby next to _____.');


INSERT INTO public.answer(
	id, answer_name, is_correct_answer)
	VALUES (1, 'it', false),
	(2, 'them', false),
	(3, 'they', true),
	(4, 'their', false),
	(5, 'Mans', false),
	(6, 'Mens', false),
	(7, 'Men', true),
	(8, 'Menses', false),
	(9, 'Persons', false),
	(10, 'People', true),
	(11, 'Peoples', false),
	(12, 'Persones', false),
	(13, 'did you arrived', false),
	(14, 'did you arrive', true),
	(15, 'were you arrived', false),
	(16, 'have you arrived', false),
	(17, 'through ', true),
	(18, 'across', false),
	(19, 'into', false),
	(20, 'to', false),
	(21, 'nothing', false),
	(22, 'anything', true),
	(23, 'something', false),
	(24, 'thing', false),
	(25, 'will open', true),
	(26, 'am going to open', false),
	(27, 'am opening', false),
	(28, 'open', false),
	(29, 'was buying', false),
	(30, 'bought', true),
	(31, 'have bought', false),
	(32, 'buyed', false),
	(33, 'live', false),
	(34, 'am living', false),
	(35, 'have lived', true),
	(36, 'was lived', false),
	(37, 'not having said', false),
	(38, 'have never said', false),
	(39, 'never said', false),
	(40, 'had never said', true),
	(41, 'to be abducted', false),
	(42, 'to be abducting', false),
	(43, 'to have been abducted', true),
	(44, 'to have been abducting', false),
	(45, 'herself', false),
	(46, 'her', true),
	(47, 'her own', false),
	(48, 'hers', false);



INSERT INTO public.question_answer(
	id, answer_id, is_correct_answer, question_id)
	VALUES (1, 1, false, 1),
	(2, 2, false, 1),
	(3, 3, true, 1),
	(4, 4, false, 1),
	
	(5, 5, false, 2),
	(6, 6, false, 2),
	(7, 7, true, 2),
	(8, 8, false, 2),
	
	(9, 9, false, 3),
	(10, 10, true, 3),
	(11, 11, false, 3),
	(12, 12, false, 3),
	
	(13, 13, false, 4),
	(14, 14, true, 4),
	(15, 15, false, 4),
	(16, 16, false, 4),
	
	(17, 17, true, 5),
	(18, 18, false, 5),
	(19, 19, false, 5),
	(20, 20, false, 5),
	
	(21, 21, false, 6),
	(22, 22, true, 6),
	(23, 23, false, 6),
	(24, 24, false, 6),
	
	(25, 25, true, 7),
	(26, 26, false, 7),
	(27, 27, false, 7),
	(28, 28, false, 7),
	
	(29, 29, false, 8),
	(30, 30, true, 8),
	(31, 31, false, 8),
	(32, 32, false, 8),
	
	(33, 33, false, 9),
	(34, 34, false, 9),
	(35, 35, true, 9),
	(36, 36, false, 9),
	
	(37, 37, false, 10),
	(38, 38, false, 10),
	(39, 39, false, 10),
	(40, 40, true, 10),
	
	(41, 41, false, 11),
	(42, 42, false, 11),
	(43, 43, true, 11),
	(44, 44, false, 11),
	
	(45, 45, false, 12),
	(46, 46, true, 12),
	(47, 47, false, 12),
	(48, 48, false, 12);

 


SELECT setval('public.users_user_id_seq', (SELECT MAX(user_id) FROM public.users));


SELECT setval('public.messages_sender_receiver_id_seq', (SELECT MAX(id) FROM public.messages_sender_receiver));








    
