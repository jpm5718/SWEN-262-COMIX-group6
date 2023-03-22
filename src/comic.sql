DROP TABLE IF EXISTS comics

CREATE TABLE comics(
    id SERIAL PRIMARY KEY,
    series TEXT,
    issue TEXT,
    title TEXT,
    var_desc TEXT, 
    publisher TEXT,
    release_date TEXT,
    format TEXT,
    added_date TEXT,
    creators TEXT
);