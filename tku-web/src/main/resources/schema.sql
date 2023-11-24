CREATE TABLE IF NOT EXISTS "USER_INFO"
(
    user_id character varying(30)  NOT NULL,
    user_name character varying(50) NOT NULL,
    role_id character varying(30) NOT NULL,
    email character varying(200) NOT NULL,
    enabled character varying(1) NOT NULL DEFAULT 1,
    password character varying(300) NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
    );
