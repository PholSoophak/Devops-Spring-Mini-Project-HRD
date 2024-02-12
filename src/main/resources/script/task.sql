create table public.task_tb
(
    task_id          serial
        primary key,
    task_name        varchar(100) not null,
    task_description varchar(255) not null,
    task_date        timestamp,
    task_status      varchar(50)  not null,
    user_id          integer
        constraint user_fk
            references public.user_tb
            on update cascade on delete cascade,
    category_id      integer
        constraint category_fk
            references public.category_tb
            on update cascade on delete cascade
);