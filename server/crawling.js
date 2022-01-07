const axios = require("axios");
const cheerio = require("cheerio");

let getHTML = async (search, check) => {
  try {
    if (check == "1") {
      console.log("a");
      return await axios.get(
        "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=" +
          search
      );
    } else {
      console.log("asdasdsadas");
      return await axios.get("https://www.google.com/search?q=" + search);
    }
  } catch (err) {
    console.log(err);
  }
};
const mclink = async (search) => {
  const product = [];
  const check = "1";
  const html = await getHTML(search, check);
  let $ = cheerio.load(html.data);
  const $error = $("#notfound");
  if (
    $error.find("div > p").text() ==
    "만족스러운 검색결과를 찾지 못하셨다면 아래 기능도 이용해 보세요."
  ) {
    console.log("여기라도 나와라 제발...");
    //Naver 상품 정보가 나오지 않는 경우 -> Google에서 검색
    const chk = "0";
    const htmls = await getHTML(search, chk);
    $ = cheerio.load(htmls.data);
    const $prodList = $("#rso > div.g");
    $prodList.each((i, node) => {
      console.log($(node).find("div > div > div.yuRUbf > a > h3").text());
      console.log("Wow");
      // if (i <= 1) {
      //   console.log("일단 첫번째 성공");
      //   product[i] = {
      //     prodname: $(node).find("div > div > div.yuRUbf > a > h3").text(), //Google 상품 이름
      //     prodlink: $(node).find("div > div > div.yuRUbf > a").attr("href"), // Google 상품 이름
      //   };
      //   console.log($(node).find("div > div > div.yuRUbf > a > h3").text());
      // }
    });
  } else {
    //Naver 상품 정보가 있는 경우
    const $prodList = $(
      "#main_pack > section.sc_new.sp_ntotal._prs_web_lis._web_lis._sp_ntotal._kin_snippet_root_web_lis._project_channel_root_web_lis._fe_kin_snippet > div > ul"
    ).children("li.bx");
    $prodList.each((i, node) => {
      if (i <= 1) {
        product[i] = {
          prodname: $(node)
            .find("div > div.total_tit_group > div.total_tit > a")
            .text(), // Naver 상품 이름
          prodlink: $(node)
            .find(
              "div > div.total_tit_group > div.total_source > div > a.txt.elss"
            )
            .attr("href"), // Naver 상품 링크
        };
      }
    });
  }
  const products = JSON.stringify(product);
  console.log(products);
};

// mclink("8809472909468"); //핸드크림 -> naver 없음.

mclink("8801062015481"); //드림카카오
