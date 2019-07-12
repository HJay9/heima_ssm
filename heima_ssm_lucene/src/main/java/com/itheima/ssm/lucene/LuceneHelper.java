package com.itheima.ssm.lucene;

import com.itheima.ssm.domain.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * lucene 相关工具类
 * @author Jay
 * @date 2019/7/12
 */
public class LuceneHelper {

    private static String index_Path = "F:\\Development\\lucene\\temp\\index";

    /**
     * 为 Product 创建索引
     * @param product
     */
    public static void createIndex(Product product) {
        IndexWriter indexWriter = null;
        try {
            //指定索引库位置
            Directory directory = FSDirectory.open(new File(index_Path).toPath());
            //使用自定义中文分析器
            indexWriter = new IndexWriter(directory, new IndexWriterConfig(new IKAnalyzer()));
            //创建索引前先删除索引库
            indexWriter.deleteAll();
            //创建 Document，并为其添加对应的 Filed
            Document document = new Document();
            document.add(new TextField("id", product.getId(), Field.Store.YES));
            document.add(new TextField("productNum", product.getProductNum(), Field.Store.YES));
            document.add(new TextField("productName", product.getProductName(), Field.Store.YES));
            document.add(new TextField("cityName", product.getCityName(), Field.Store.YES));
            document.add(new TextField("departureTimeStr", product.getDepartureTimeStr(), Field.Store.YES));
            document.add(new TextField("productPrice", product.getProductPrice() + "", Field.Store.YES));
            document.add(new TextField("productDesc", product.getProductDesc(), Field.Store.YES));
            document.add(new TextField("productStatusStr", product.getProductStatusStr(), Field.Store.YES));
            //将 document 写入索引库
            indexWriter.addDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭 indexWriter
            try {
                if (indexWriter != null) {
                    indexWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 为 List<Product> 创建索引
     * @param productList
     */
    public static void createIndex(List<Product> productList) {
        IndexWriter indexWriter = null;
        try {
            //指定索引库位置
            Directory directory = FSDirectory.open(new File(index_Path).toPath());
            //使用自定义中文分析器
            indexWriter = new IndexWriter(directory, new IndexWriterConfig(new IKAnalyzer()));
            //创建索引前先删除索引库
            indexWriter.deleteAll();
            for (Product product : productList) {
                //创建 Document，并为其添加对应的 Filed
                Document document = new Document();
                document.add(new TextField("id", product.getId(), Field.Store.YES));
                document.add(new TextField("productNum", product.getProductNum(), Field.Store.YES));
                document.add(new TextField("productName", product.getProductName(), Field.Store.YES));
                document.add(new TextField("cityName", product.getCityName(), Field.Store.YES));
                document.add(new TextField("departureTimeStr", product.getDepartureTimeStr(), Field.Store.YES));
                document.add(new TextField("productPrice", product.getProductPrice() + "", Field.Store.YES));
                document.add(new TextField("productDesc", product.getProductDesc(), Field.Store.YES));
                document.add(new TextField("productStatusStr", product.getProductStatusStr(), Field.Store.YES));
                //将 document 写入索引库
                indexWriter.addDocument(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭 indexWriter
            try {
                if (indexWriter != null) {
                    indexWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关键字查询
     * @param selectKey
     * @return
     */
    public static List<Product> searchIndex(String selectKey) {
        List<Product> productList = null;
        IndexReader indexReader = null;
        try {
            productList = new ArrayList<>();
            Directory directory = FSDirectory.open(new File(index_Path).toPath());
            indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //使用多域查询
            Query query = new MultiFieldQueryParser(
                    new String[]{"productName", "productDesc", "cityName", "productPrice"}, new IKAnalyzer()).parse(selectKey);
            //执行查询
            TopDocs topDocs = indexSearcher.search(query, 10);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                //根据 id 获得 document 对象
                Document document = indexReader.document(scoreDoc.doc);
                //将 document 里的数据封装进 product
                Product product = new Product();
                product.setId(document.get("id"));
                product.setProductNum(document.get("productNum"));
                product.setProductName(document.get("productName"));
                product.setCityName(document.get("cityName"));
                product.setDepartureTimeStr(document.get("departureTimeStr"));
                product.setProductPrice(Double.parseDouble(document.get("productPrice")));
                product.setProductDesc(document.get("productDesc"));
                product.setProductStatusStr(document.get("productStatusStr"));
                //封装到 list 集合
                productList.add(product);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (indexReader != null) {
                    indexReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    /**
     * 关键字查询
     * @param
     * @return
     */
    public static List<Product> searchIndex() {
        List<Product> productList = null;
        IndexReader indexReader = null;
        try {
            productList = new ArrayList<>();
            Directory directory = FSDirectory.open(new File(index_Path).toPath());
            indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            //使用多域查询
            Query query = new MatchAllDocsQuery();
            //执行查询
            TopDocs topDocs = indexSearcher.search(query, 10);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                //根据 id 获得 document 对象
                Document document = indexReader.document(scoreDoc.doc);
                //将 document 里的数据封装进 product
                Product product = new Product();
                product.setId(document.get("id"));
                product.setProductNum(document.get("productNum"));
                product.setProductName(document.get("productName"));
                product.setCityName(document.get("cityName"));
                product.setDepartureTimeStr(document.get("departureTimeStr"));
                product.setProductPrice(Double.parseDouble(document.get("productPrice")));
                product.setProductDesc(document.get("productDesc"));
                product.setProductStatusStr(document.get("productStatusStr"));
                //封装到 list 集合
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (indexReader != null) {
                    indexReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

}
