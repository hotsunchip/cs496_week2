var mysql = require("mysql"); // mysql 모듈 로드
var db_info = {
  // mysql 접속 설정
  host: "localhost",
  port: "3306",
  user: "suminkim1317@naver.com",
  password: "!",
  database: "mc2_users",
};

module.exports = {
  init: function () {
    return mysql.createConnection(db_info);
  },
  connect: function (conn) {
    conn.connect(function (err) {
      if (err) console.error("mysql connection error : " + err);
      else console.log("mysql is connected successfully!");
    });
  },
};
