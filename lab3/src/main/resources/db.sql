DROP TABLE IF EXISTS lab3_x_test_table;

CREATE TABLE lab3_x_test_table (
    id serial primary key,
    XValue double precision,
    YValue double precision,
    RValue double precision,
    Result bool,
    Watch timestamptz,
    duration double precision,
    SessionID char(40)
);