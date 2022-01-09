var express = require("express");
var router = express.Router();

//Crawling 연결
const crawlingdata = require("../crawling.js");
async function getData(barcode) {
  const bookdata = await crawlingdata.getBook(barcode);
  console.log(bookdata);
}
getData("9791158391508");

//Database 구축
var mysql = require("mysql");
const dbconfig = require("../config/database.js");
const connection = mysql.createConnection(dbconfig);

router.get("/get", function (req, res, next) {
  console.log("GET 호출 / data : " + req.query.data);
  console.log("path : " + req.path);
  res.send("get success");
});

router.post("/post", function (req, res, next) {
  console.log("POST 호출 / data : " + req.body.data);
  console.log("path : " + req.path);
  res.send("post success");
});

router.put("/put/:id", function (req, res, next) {
  console.log("UPDATE 호출 / id : " + req.params.id);
  console.log("body : " + req.body.data);
  console.log("path : " + req.path);
  res.send("put success");
});

router.delete("/delete/:id", function (req, res, next) {
  console.log("DELETE 호출 / id : " + req.params.id);
  console.log("path : " + req.path);
  res.send("delete success");
});

module.exports = router;
