package com.miniproject.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.miniproject.model.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class CrawlingTest {

	@Test
	public void getData() {
		String url = "https://flowersonyou.co.kr/product/list.html?cate_no=26";

		Document doc;
		try {
			doc = Jsoup.connect(url).get();
//			System.out.println(doc);

			Elements elements = doc.select(".df-prl-item");

//			System.out.println(elements);
//			System.out.println(elements.size());
//			System.out.println(elements.text());

			for (Element e : elements) {

				String itemName = e.select(".df-prl-name span").get(1).text();
				String tmpPrice = e.select(".product_price span").get(1).text();

				int price = Integer.parseInt(tmpPrice.replaceAll("[^0-9]", ""));

				
				Item item = new Item(itemName, price);
				
				System.out.println(item);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
