if ( 'prettyPrint' in window ) {} else {
    document.write( '<script type="text/javascript" src="http://gist-it.appspot.com/assets/prettify/prettify.js"></script>' );
}


document.write( '<link rel="stylesheet" href="http://gist-it.appspot.com/assets/embed.css"/>' );


document.write( '<link rel="stylesheet" href="http://gist-it.appspot.com/assets/prettify/prettify.css"/>' );

document.write( '<div class="gist-it-gist">\n<div class="gist-file">\n    <div class="gist-data">\n        \n        <pre class="prettyprint">public class Word2VecExample {\n    private SentenceIterator iter;\n    private TokenizerFactory tokenizer;\n    private Word2Vec vec;\n\n    public final static String VEC_PATH = "vec2.ser";\n    public final static String CACHE_SER = "cache.ser";\n\n    public Word2VecExample(String path) throws Exception {\n        this.iter = new LineSentenceIterator(new File(path));\n        tokenizer =  new DefaultTokenizerFactory();\n    }\n\n    public static void main(String[] args) throws Exception {\n        if(args.length &gt;= 1)\n            new Word2VecExample(args[0]).train();\n        else {\n            ClassPathResource resource = new ClassPathResource("raw_sentences.txt");\n            File f = resource.getFile();\n            new Word2VecExample(f.getAbsolutePath()).train();\n        }\n    }\n\n\n    public void train() throws Exception {\n        VocabCache cache;\n        if(vec == null &amp;&amp; !new File(VEC_PATH).exists()) {\n            cache = new InMemoryLookupCache.Builder()\n                    .lr(2e-5).vectorLength(100).build();\n\n            vec = new Word2Vec.Builder().minWordFrequency(5).vocabCache(cache)\n                    .windowSize(5)\n                    .layerSize(100).iterate(iter).tokenizerFactory(tokenizer)\n                    .build();\n            vec.setCache(cache);\n            vec.fit();\n\n\n            SerializationUtils.saveObject(vec, new File(VEC_PATH));\n            SerializationUtils.saveObject(cache,new File(CACHE_SER));\n\n        }\n\n\n        else {\n            vec = SerializationUtils.readObject(new File(VEC_PATH));\n            cache = SerializationUtils.readObject(new File(CACHE_SER));\n            vec.setCache(cache);\n\n            for(String s : cache.words()) {\n                System.out.println(s);\n            }\n\n            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));\n            String line;\n            System.out.println("Print similarity");\n            while((line = reader.readLine()) != null) {\n\n                String[] split = line.split(",");\n                if(cache.indexOf(split[0]) &lt; 0) {\n                    System.err.println("Word " + split[0] + " not in vocab");\n                    continue;\n                }\n                if(cache.indexOf(split[01]) &lt; 0) {\n                    System.err.println("Word " + split[1] + " not in vocab");\n                    continue;\n                }\n                System.out.println(vec.similarity(split[0],split[1]));\n            }\n        }</pre>\n        \n    </div>\n    \n    <div class="gist-meta">\n        \n        <span><a href="https://github.com/SkymindIO/dl4j-examples/blob/master/src/main/java/org/deeplearning4j/word2vec/Word2VecExample.java">This Gist</a> brought to you by <a href="http://gist-it.appspot.com">gist-it</a>.</span>\n        \n        <span style="float: right; color: #369;"><a href="https://github.com/SkymindIO/dl4j-examples/raw/master/src/main/java/org/deeplearning4j/word2vec/Word2VecExample.java">view raw</a></span>\n        <span style="float: right; margin-right: 8px;">\n            <a style="color: rgb(102, 102, 102);" href="https://github.com/SkymindIO/dl4j-examples/blob/master/src/main/java/org/deeplearning4j/word2vec/Word2VecExample.java">Word2VecExample.java</a></span>\n            <!-- Generated by: http://gist-it.appspot.com -->\n    </div>\n    \n</div>\n</div>' );

document.write( '<script type="text/javascript">prettyPrint();</script>' );