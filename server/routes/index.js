var express = require("express");
var router = express.Router();
//Database 구축
var mysql = require("mysql");
const dbconfig = require("../config/database.js");
const connection = mysql.createConnection(dbconfig);

/* GET home page. */
router.get("/", function (req, res, next) {
  //Database 구축
  connection.query("SELECT * from book", (error, rows) => {
    if (error) throw error;
    console.log("User info is: ", rows);
    res.send(rows);
  });
  // res.status(200).json({
  //   success: true,
  // });
});

router.get("/usersss", function (req, res) {
  console.log("who get in here / users");
  res.status(200).json({
    message: "test",
  });
});

router.post("/post", function (req, res) {
  console.log("who get in here post / users");
  var inputData;
  req.on("data", (data) => {
    inputData = JSON.parse(data);
  });
  req.on("end", () => {
    console.log(
      "user_id : " + inputData.user_id + " , name : " + inputData.name
    );
  });
  res.write("OK!");
  res.end();
});

module.exports = router;
