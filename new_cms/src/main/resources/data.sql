INSERT INTO departments (code, name, contactEmail) VALUES ('MIE', 'Mechanical and Industrial Engineering', 'reception@mie.utoronto.ca');
INSERT INTO departments (code, name, contactEmail) VALUES ('ECE', 'Electrical and Computer Engineering', 'eceinquiry@utoronto.ca');
INSERT INTO departments (code, name, contactEmail) VALUES ('MSE', 'Materials Science and Engineering', 'materials.engineering@utoronto.ca');
INSERT INTO universities (universityId, uniName) VALUES ('1','UOFT');

INSERT INTO QSRanking (QS_ranking_id, Institution_Name, Location, Academic_Reputation_Score, Employer_Reputation_Score, Faculty_Student_Score, Citations_per_Faculty_Score, International_Faculty_Score, International_Students_Score, International_Research_Network_Score, Employment_Outcomes_Score, Overall_Score) VALUES ('1', 'Massachusetts Institute of Technology (MIT)', 'United States', '100', '100', '100', '100', '100', '90', '96.1', '100', '100');
INSERT INTO QSRanking (QS_ranking_id, Institution_Name, Location, Academic_Reputation_Score, Employer_Reputation_Score, Faculty_Student_Score, Citations_per_Faculty_Score, International_Faculty_Score, International_Students_Score, International_Research_Network_Score, Employment_Outcomes_Score, Overall_Score) VALUES ('2', 'University of Cambridge', 'United Kingdom', '100', '100', '100', '92.3', '100', '96.3', '99.5', '100', '98.8');
INSERT INTO QSRanking (QS_ranking_id, Institution_Name, Location, Academic_Reputation_Score, Employer_Reputation_Score, Faculty_Student_Score, Citations_per_Faculty_Score, International_Faculty_Score, International_Students_Score, International_Research_Network_Score, Employment_Outcomes_Score, Overall_Score) VALUES ('3', 'testinge', 'United Kingdom', '10656', '16600', '166600', '96662.3', '100', '96.3', '99.5', '100', '98.8');

INSERT INTO students (id, firstName, lastName, email) VALUES (1111, 'Tyrion', 'Lannister', 'tyrion.lannister@mail.univ.ca');
INSERT INTO students (id, firstName, lastName, email) VALUES (2222, 'Cersei', 'Lannister', 'cersei.lannister@mail.univ.ca');
INSERT INTO students (id, firstName, lastName, email) VALUES (3333, 'Jaime', 'Lannister', 'jaime.lannister@mail.univ.ca');
INSERT INTO students (id, firstName, lastName, email) VALUES (4444, 'Daenerys', 'Targaryen', 'jaime.targaryen@mail.univ.ca');
INSERT INTO students (id, firstName, lastName, email) VALUES (5555, 'Jon', 'Snow', 'jon.snow@mail.univ.ca');

INSERT INTO professors (id, firstName, lastName, email, office) VALUES (1122, 'Sansa', 'Stark', 'sansa.stark@univ.ca', 'BA1234');
INSERT INTO professors (id, firstName, lastName, email, office) VALUES (3344, 'Arya', 'Stark', 'arya.stark@univ.ca', 'MC1234');
INSERT INTO professors (id, firstName, lastName, email, office) VALUES (5566, 'Jorah', 'Mormont', 'jorah.mormont@univ.ca', 'MY1234');
