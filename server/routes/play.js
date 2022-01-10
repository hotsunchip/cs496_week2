var express = require("express");
var router = express.Router();

//Database 구축
var mysql = require("mysql");
const dbconfig = require("../config/database.js");
const connection = mysql.createConnection(dbconfig);

//Crawling 연결
const crawlingdata = require("../crawling.js");

// 바코드 찍었을 때 DB에 저장 -> req.body.codenum, req.body.userid
router.get("/barcodecheck", function (req, res) {
  let codenum = req.body.codenum;
  let userid = req.body.userid;
  let getData = async (codenum) => {
    let bookdata = await crawlingdata.getBook(codenum);
    let sqlwrite =
      "INSERT INTO books (userid, codenum, title, author, price, review, love, imgbook, payone, paytwo, paythree, payfour, aboutbook) VALUES ('" +
      userid +
      "', '" +
      bookdata.codenum +
      "', '" +
      bookdata.title +
      "', '" +
      bookdata.author +
      "', '" +
      bookdata.price +
      "', '" +
      bookdata.review +
      "', '" +
      bookdata.love +
      "', '" +
      bookdata.imgbook +
      "', '" +
      bookdata.payone +
      "', '" +
      bookdata.paytwo +
      "', '" +
      bookdata.paythree +
      "', '" +
      bookdata.payfour +
      "', '" +
      bookdata.aboutbook +
      "');";
    connection.query(sqlwrite, (error, rows) => {
      if (error) throw error;
      console.log(rows);
    });
    connection.end();

    //respond 내용 -> codenum 원격에서 저장해야 함 (삭제 때 이용)
    res.json({
      codenum: bookdata.codenum,
      title: bookdata.title,
      author: bookdata.author,
      price: bookdata.price,
      review: bookdata.review,
      love: bookdata.love,
      imgbook: bookdata.imgbook,
      payone: bookdata.payone,
      paytwo: bookdata.paytwo,
      paythree: bookdata.paythree,
      payfour: bookdata.payfour,
      aboutbook: bookdata.aboutbook,
    });
  };
  getData(codenum);
});

//이용기록 삭제했을 때 db에서 delete -> req.body.codenum, req.body.userid
router.delete("/barcodedelete", function (req, res) {
  let codenum = req.body.codenum;
  let userid = req.body.userid;
  let sqlwrite =
    "DELETE FROM books WHERE books.codenum = '" +
    codenum +
    "' AND books.userid = '" +
    userid +
    "';";
  connection.query(sqlwrite, (error, rows) => {
    if (error) throw error;
    console.log(rows);
  });
  connection.end();
  res.send("delete success");
});
