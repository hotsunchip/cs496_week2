var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");

var indexRouter = require("./routes/index");
var usersRouter = require("./routes/users");

var app = express();

// const port = process.env.PORT || 8080
const http = require("http");

const hostname = "127.0.0.1";
const port = 3000;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader("Content-Type", "text/plain");
  res.end("화이팅");
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

//database 구축 실험 코드
var db_config = require(__dirname + "/config/database.js");
var conn = db_config.init();
var bodyParser = require("body-parser");
db_config.connect(conn);

app.set("views", __dirname + "/views");
app.set("view engine", "ejs");
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.get("/", function (req, res) {
  res.send("ROOT");
});

app.get("/list", function (req, res) {
  var sql = "SELECT * FROM mc2_users";
  conn.query(sql, function (err, rows, fields) {
    if (err) console.log("query is not excuted. select fail...\n" + err);
    else res.render("list.ejs", { list: rows });
  });
});

app.get("/write", function (req, res) {
  res.render("write.ejs");
});

app.post("/writeAf", function (req, res) {
  var body = req.body;
  console.log(body);
  var sql = "INSERT INTO BOARD VALUES(?, ?, ?, NOW())";
  var params = [body.userid, body.userpw];
  console.log(sql);
  conn.query(sql, params, function (err) {
    if (err) console.log("query is not excuted. insert fail...\n" + err);
    else res.redirect("/list");
  });
});
// app.listen(3000, () => console.log('Server is running on port 3000...'));

// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "jade");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

app.use("/", indexRouter);
app.use("/users", usersRouter);

// app.listen(port, () =>
//   console.log("Example app listening at http://127.0.0.1:8080")
// );

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render("error");
});

module.exports = app;
