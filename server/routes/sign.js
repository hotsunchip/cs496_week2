var express = require("express");
var router = express.Router();

//Database 구축
var mysql = require("mysql");
const dbconfig = require("../config/database.js");
const connection = mysql.createConnection(dbconfig);

//회원가입 아이디 중복 확인 -> req.body.userid
router.get("/idcheck", function (req, res) {
  let checkid = req.body.userid;
  let sqlwrite = "select userid from users;";
  connection.query(sqlwrite, (error, results) => {
    var resultCode = 404;
    var message = "에러가 발생했습니다.";
    if (error) throw error;
    var check = true;
    for (let i = 0; i < results.length; i++) {
      if (results[i].userid == checkid) {
        check = false;
      }
    }
    if (check == false) {
      resultCode = 204;
      message = "중복된 id가 존재합니다.";
    } else {
      resultCode = 200;
      message = "사용 가능한 id입니다.";
    }
    res.json({
      code: resultCode,
      message: message,
    });
  });
  connection.end();
});

//회원가입 진행 -> req.body.userid, req.body.userpw, req.body.nickname
router.post("/signup", function (req, res) {
  let userid = req.body.userid;
  let userpw = req.body.userpw;
  let nickname = req.body.nickname;
  let sqlwrite =
    "INSERT INTO users (userid, userpw, nickname) VALUES ('" +
    userid +
    "', '" +
    userpw +
    "', '" +
    nickname +
    "');";
  connection.query(sqlwrite, (error, rows) => {
    var resultCode = 404;
    var message = "에러가 발생했습니다.";
    if (error) {
      console.log(error);
    } else {
      resultCode = 200;
      message = "회원가입에 성공했습니다.";
      console.log(rows);
    }
    res.json({
      code: resultCode,
      message: message,
    });
  });
  connection.end();
});


module.exports = router;