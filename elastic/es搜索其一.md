## 搜索

1. term是es中最小的查询单位

   ```tiki wiki
    当我们插入数据是，默认的mapping会设置类型为text进行分词，而keyword不会分词处理.
    所以term查询，是精准匹配。如果text想进行term查询，可以使用.keyword属性。
    term ---> constant_score 去除打分
    GET kibana_sample_data_ecommerce/_search
   {
     "query": {
       "constant_score": {
         "filter": {
           "term": {
         "customer_first_name.keyword": "Eddie"
         }
         },
         "boost": 1.2
       }
     }
   }
    trems ---> 类似 in () 包含的关系
   GET kibana_sample_data_ecommerce/_search
   {
     "query": {
       "constant_score": {
         "filter": {
           "terms": {
         "customer_first_name.keyword": ["Eddie","Mary"]
         }
         },
         "boost": 1.2
       }
     }
   }
   
   ```

   2. match 全文检索

      ```
      match 查询是一种boolean类型的查询，依赖analyzer分词实现。如果查询数据与预期结果不同，可以考虑自定义分词。
      operator 包含and or 默认是or。
      GET kibana_sample_data_ecommerce/_search
      {
        "query": {
         "match": {
           "products.product_name": {
             "query": "white skirt",
             "operator": "and"
           }
         }
        },
        "_source": "products.product_name"
      }
      也可以使用minimum_should_match：2 ，意为2个分词都要满足，才返回。
      match的查询会对传入的参数也进行分词，当结果匹配分词词组 white skirt
      结果才会被检索，不受词组顺序的影响。
      ```

   3. match phrase 检索

      ```
      match phrase 和 match 最大的区别是分词，match phrase 将将查询条件的中的信息看做一个整体，如"white skirt" 就必须white在前，中间空格，skirt在后面。
      GET kibana_sample_data_ecommerce/_search
      {
        "query": {
         "match_phrase": {
           "products.product_name": {
             "query": " Pencil black",
             "slop": 1
           }
         }
        },
        "_source": "products.product_name"
      }
      slop 来控制单词中间的间隔，默认为 0，当设置为1，有了21条数据。查询结果："Pencil skirt - black"
      
      ```

   4. range 查询类似数据库中的 大于、小于范围查询

      ```
      主要想说一下关于Date的查询：Date Math Expressions
      时间单位： y,M,w,d,H/h,m,s
      举例： 2021-01-01 00:00:00||+1M
      POST kibana_sample_data_ecommerce/_search
      {
        "query": {
          "range": {
            "order_date": {
              "gte": "now-1M"
            }
          }
        },
        "from":1,
        "size": 2
      }
      ```

   5. minimum_should_match 

      ```sh
      https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-minimum-should-match.html
      https://yuerblog.cc/2018/09/07/elasticsearch-minimum_should_match/
      针对多子句条件匹配的情况。
      ```

END（分值）：

```sh
某些查询中会有算分操作，如term，match（返回max_score，_score）
另外一些不会算分：如range。
我们数据的返回时默认按照打分倒序返回，那么打分是如何实现的呢？
首先了解一些专业名词： 

Term Frequency 词频（ TF ）：（某词在文档中出现的次数/文档的总词量）
Stop Word（SW）： 没有具体意义的词，如助词，语气词等不纳入计算范围
Inverse document frequency 逆文档频率（IDF ）：log（语料库中文档总数/包含该词的文档数+1）
计算公式：TF-IDF权值 = TF*IDF 有时会加入SW，提升精准性。

ES 5.x之后采用了BM25 算法，曲线更加的平滑。
```

 