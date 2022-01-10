var express = require("express");
var router = express.Router();

//Database 구축
var mysql = require("mysql");
const dbconfig = require("../config/database.js");
const connection = mysql.createConnection(dbconfig);

//heart를 클릭했을 때 love를 "T"로 변경 -> req.body.codenum, req.body.userid
router.get("/heartplus", function (req, res) {
  let codenum = req.body.codenum;
  let userid = req.body.userid;
  let sqlwrite =
    "UPDATE books SET love='T' WHERE books.codenum = '" +
    codenum +
    "'AND books.userid = '" +
    userid +
    "';";
  connection.query(sqlwrite, (error, rows) => {
    if (error) throw error;
    console.log(rows);
  });
  connection.end();
  res.send("heart+ success");
});

//heart를 클릭했을 때 love를 "F"로 변경 -> req.body.codenum, req.body.userid
router.get("/heartminus", function (req, res) {
  let codenum = req.body.codenum;
  let userid = req.body.userid;
  let sqlwrite =
    "UPDATE books SET love='F' WHERE books.codenum = '" +
    codenum +
    "'AND books.userid = '" +
    userid +
    "';";
  connection.query(sqlwrite, (error, rows) => {
    if (error) throw error;
    console.log(rows);
  });
  connection.end();
  res.send("heart- success");
});
