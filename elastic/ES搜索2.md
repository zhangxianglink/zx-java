## ES搜索

1. 条件组合查询

   ```
   GET kibana_sample_data_ecommerce/_search
   {
     "query": {
       "bool": {
         "must": [
           {"match": {
             "products.product_name": "black"
           }}
         ],
         "filter": [
           {"term": {
             "customer_first_name.keyword": "George"
           }}
         ],
         "must_not": [
           {"term": {
             "day_of_week": {
               "value": "Sunday"
             }
           }}
         ],
         "should": [
           {"range": {
             "products.price": {
               "gte": 10
             }
           }
           },{
             "range": {
               "products.min_price": {
                 "lte": 15
               }
             }
           }
         ],
         "minimum_should_match": 2 // must 不存在时生效
       }
     }
   }
   ```

2. 权重分析（提升精准度）

   ```
   GET kibana_sample_data_ecommerce/_search
   {
     "query": {
       "boosting": {
         "positive": {  // 正向
           "term": {
             "day_of_week": {
               "value": "Monday"
             }
           }
         },
         "negative": {  // 反向
           "range": {
             "products.price": {
               "gte": 10,
               "lte": 20
             }
           }
         },
         "negative_boost": 0.2  // 加权查询·
       }
     }
   }
   https://blog.csdn.net/questiontoomuch/article/details/48494241?spm=1001.2014.3001.5502
   ```

3. 单字符串，多字段查询

   ```
   https://blog.csdn.net/questiontoomuch/article/details/48493991?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242
   ```

4. 多语言查询

   ```
   PUT /blogs_completion/
   {
     "mappings": {
       "tech": {
         "properties": {
           "body": {
             "type": "completion",
             "analyzer": "english" // ik hanlp
           }
         }
       }
     }
   }
   Hanlp 分词
   IK    分词
   在初始化索引时指定分词，如有需要可以自定义词典。
    _analyze 分析有助于search template 的编写
   ```

5. 自动补全https://blog.csdn.net/wwd0501/article/details/80595201

   ```
   suggester API
   基础 term
   拓展 phrase
   高级 Completion、context
   
   POST /blogs/_search
   { 
     "suggest": {
       "my-suggestion": {
         "text": "lucne rock",
         "term": {
           "suggest_mode": "missing",
           "field": "body"
         }
       }
     }
   }
   
   suggest就是一种特殊类型的搜索，DSL内部的"text"指的是api调用方提供的文本，也就是通常用户界面上用户输入的内容。这里的lucne是错误的拼写，模拟用户输入错误。 "term"表示这是一个term suggester。 "field"指定suggester针对的字段，另外有一个可选的"suggest_mode"。 范例里的"missing"实际上就是缺省值，也可将"suggest_mode"换成"popular"
   Phrase suggester在Term suggester的基础上，会考量多个term之间的关系，比如是否同时出现在索引的原文里，相邻程度，以及词频等等。看个范例就比较容易明白了:
   
   POST /blogs/_search
   {
     "suggest": {
       "my-suggestion": {
         "text": "lucne and elasticsear rock",
         "phrase": {
           "field": "body",
           "highlight": {
             "pre_tag": "<em>",
             "post_tag": "</em>"
           }
         }
       }
     }
   }
   使用Completion Suggester，字段的类型需要专门定义如下:
   
   PUT /blogs_completion/
   {
     "mappings": {
       "tech": {
         "properties": {
           "body": {
             "type": "completion"
             "analyzer": "english"
           }
         }
       }
     }
   }使用Completion Suggester，字段的类型需要专门定义如下:
   
   PUT /blogs_completion/
   {
     "mappings": {
       "tech": {
         "properties": {
           "body": {
             "type": "completion"
           }
         }
       }
     }
   }
   POST _bulk/?refresh=true
   { "index" : { "_index" : "blogs_completion", "_type" : "tech" } }
   { "body": "Lucene is cool"}
   { "index" : { "_index" : "blogs_completion", "_type" : "tech" } }
   { "body": "Elasticsearch builds on top of lucene"}
   { "index" : { "_index" : "blogs_completion", "_type" : "tech" } }
   { "body": "Elasticsearch rocks"}
   { "index" : { "_index" : "blogs_completion", "_type" : "tech" } }
   { "body": "Elastic is the company behind ELK stack"}
   { "index" : { "_index" : "blogs_completion", "_type" : "tech" } }
   { "body": "the elk stack rocks"}
   { "index" : { "_index" : "blogs_completion", "_type" : "tech" } }
   { "body": "elasticsearch is rock solid"}
   POST blogs_completion/_search?pretty
   { "size": 0,
     "suggest": {
       "blog-suggest": {
         "prefix": "elastic i",
         "completion": {
           "field": "body"
         }
       }
     }
   }
   ```

   