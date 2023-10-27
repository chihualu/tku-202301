CREATE DATABASE tku;



CREATE TABLE IF NOT EXISTS public."user"
(
    user_id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    user_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    role_id character varying(30) COLLATE pg_catalog."default" NOT NULL,
    email character varying(200) COLLATE pg_catalog."default" NOT NULL,
    enabled character varying(1) COLLATE pg_catalog."default" NOT NULL DEFAULT 1,
    password character varying(300) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
    )


select * from public.user;

insert into public.user values('kenny', 'Kenny Lu', 'USER', 'chihualu@gmail.com', '1', '123456')