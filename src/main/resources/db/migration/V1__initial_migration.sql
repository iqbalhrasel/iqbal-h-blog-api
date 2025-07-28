create table users(
    id integer GENERATED ALWAYS AS IDENTITY,
    name varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(64),
    primary key(id)
);

create table topics(
    id smallint GENERATED ALWAYS AS IDENTITY,
    name varchar(64),
    primary key(id)
);

create table blogs(
    id integer GENERATED ALWAYS AS IDENTITY,
    topic_id smallint not null,
    author_id integer not null,
    created_at TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
    last_modified TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
    url varchar(255) not null,
    title varchar(255) not null,
    meta_text varchar(255) not null,
    content text,
    status varchar(32) not null,
    primary key(id),
    foreign key(topic_id) references topics(id),
    foreign key(author_id) references users(id) ON DELETE CASCADE
);

create table comments(
    id integer GENERATED ALWAYS AS IDENTITY,
    blog_id integer,
    created_at TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
    name varchar(255) not null,
    email varchar(255) not null,
    content text not null,
    primary key(id),
    foreign key(blog_id) references blogs(id) ON DELETE CASCADE
);

create table interview_questions(
    id integer GENERATED ALWAYS AS IDENTITY,
    topic_id smallint,
    created_at TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
    question varchar(255) not null,
    answer text not null,
    status varchar(32) not null,
    primary key(id),
    foreign key(topic_id) references topics(id)
);

create table tokens(
    id integer GENERATED ALWAYS AS IDENTITY,
    jwt_token text not null,
    token_type varchar(32) not null,
    revoked boolean not null,
    user_id integer not null,
    primary key(id),
    foreign key(user_id) references users(id)
);