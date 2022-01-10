var express = require("express");
var router = express.Router();

//Database 구축
var mysql = require("mysql");
const dbconfig = require("../config/database.js");
const connection = mysql.createConnection(dbconfig);

//로그인 클릭 시 -> req.body.userid, req.body.userpw
router.get("/signcheck", function (req, res) {
  let checkid = req.body.userid;
  let checkpw = req.body.userpw;
  var userid = "id";
  var userpw = "pw";
  let sqlwrite = "select userid, userpw from users;";
  connection.query(sqlwrite, (error, results) => {
    var resultCode = 404;
    var message = "에러가 발생했습니다.";
    if (error) throw error;
    var check = false;
    for (let i = 0; i < results.length; i++) {
      if (results[i].userid == checkid) {
        check = true;
        if (results[i].userpw == checkpw) {
          userid = results[i].userid;
          userpw = results[i].userpw;
          resultCode = 200;
          message = userid + "님 환영합니다!";
          break;
        } else {
          userid = "pw";
          resultCode = 204;
          message = "비밀번호가 틀렸습니다.";
          break;
        }
      }
    }
    if (!check) {
      resultCode = 204;
      message = "존재하지 않는 계정입니다.";
    }
    res.json({
      code: resultCode,
      message: message,
    });
  });
  connection.end();
});

//로그인하면 db에서 책 정보 가져오기 -> req.body.userid
router.get("/signin", function (req, res) {
  let userid = req.body.userid;
  let sqlwrite =
    "SELECT userid, codenum, title, author, price, review, love, imgbook, payone, paytwo, paythree, payfour, aboutbook FROM books WHERE userid = '" +
    userid +
    "';";
  connection.query(sqlwrite, (error, results) => {
    if (error) throw error;
    res.send(results);
  });
});

//계정 삭제 -> req.body.userid
router.delete("/bye", function (req, res) {
  let userid = req.body.userid;
  let sqlwrite =
    "DELETE users, books FROM books RIGHT JOIN users ON books.userid = users.userid WHERE users.userid = '" +
    userid +
    "';";
  connection.query(sqlwrite, (error, rows) => {
    if (error) throw error;
    console.log(rows);
  });
  connection.end();
  res.send("delete success");
});
