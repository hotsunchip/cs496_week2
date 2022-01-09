var express = require("express");
var router = express.Router();

/* GET users listing. */
router.post('/join', function (req, res) {
  var userEmail = req.body.userEmail;
  var userPwd = req.body.userPwd;
  var userName = req.body.userName;

  // 삽입을 수행하는 sql문.
  var sql = 'INSERT INTO Users (UserEmail, UserPwd, UserName) VALUES (?, ?, ?)';
  var params = [userEmail, userPwd, userName];

  // sql 문의 ?는 두번째 매개변수로 넘겨진 params의 값으로 치환된다.
  connection.query(sql, params, function (err, result) {
    var resultCode = 404;
    var message = '에러가 발생했습니다';

    if (err) {
      console.log(err);
    } else {
      resultCode = 200;
      message = '회원가입에 성공했습니다.';
    }

    res.json({
      'code': resultCode,
      'message': message
    });
  });
});

router.post('/login', function (req, res) {
  console.log(req.body);
  var userEmail = req.body.userEmail;
  var userPwd = req.body.userPwd;
  var sql = 'select * from Users where UserEmail = ?';

  connection.query(sql, userEmail, function (err, result) {
    var resultCode = 404;
    var message = '에러가 발생했습니다';

    if (err) {
      console.log(err);
    } else {
      if (result.length === 0) {
        resultCode = 204;
        message = '존재하지 않는 계정입니다!';
      } else if (userPwd !== result[0].UserPwd) {
        resultCode = 204;
        message = '비밀번호가 틀렸습니다!';
      } else {
        resultCode = 200;
        message = '로그인 성공! ' + result[0].UserName + '님 환영합니다!';
      }
    }

    res.json({
      'code': resultCode,
      'message': message
    });
  })
});



module.exports = router;
