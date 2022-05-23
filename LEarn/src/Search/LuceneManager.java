/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * Sebuah class yang menangani pencarian pertanyaan berdasarakan kata yang dimasukan.
 * Kelas ini menggunakan singelton pattern untuk mendapatkan instancenya.
 * @author Anas
 */
public class LuceneManager {
    
    private final StandardAnalyzer analyzer;
    private Directory index;
    private ArrayList<Pair<Integer, String>> result;
    private boolean hasSeacrh = false;
    
    private static LuceneManager instance = new LuceneManager();

    private LuceneManager() {
        analyzer = new StandardAnalyzer();
        index = new RAMDirectory();
        result = new ArrayList<>();
        
        instance = this;
    }    

    /**
     * Mendapatkan static instance object dari kelas ini
     * @return instance dari kelas
     */
    public static LuceneManager getInstance() {
        return instance;
    }
    
    private void AddItemToSearch(String question, int indexQuestion){
        try {
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter w = new IndexWriter(index, config);
            addDoc(w, question, indexQuestion);
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(LuceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AddItem(String question, int indexQuestion){
        if(hasSeacrh){
            index = new RAMDirectory();
            hasSeacrh = false;
            result = new ArrayList<>();
        }
            
        AddItemToSearch(question, indexQuestion);
    }
    
    /**
     * Method untuk memasukan text text question yang akan dilakukan pencarian
     * @param item : sebuah list yang berisi text question
     */
    public void AddItemToSearch(ArrayList<String> item){
        
        if(hasSeacrh){
            index = new RAMDirectory();
            hasSeacrh = false;
            result = new ArrayList<>();
        }
        
        for(int i = 0; i < item.size(); i++){
            AddItemToSearch(item.get(i), i);
        }
    }
    
    /**
     * Method untuk melakukan pencarian kata pada file yang telah didaftarkan
     * @param querystr : kata yang ingin dicari
     * @return true jika kata yang dicari berada pada text yang telah didaftarkan, false jika kata yang dicari tidak ada pada text yang telah didaftarkan
     */
    public boolean SearchResult(String querystr){
        boolean isFound = false;
        try {
            // the "question" arg specifies the default field to use
            // when no field is explicitly specified in the query.
            Query q = new QueryParser("question", analyzer).parse(querystr);
            
            // 3. search
            int hitsPerPage = 10;
            try (IndexReader reader = DirectoryReader.open(index)) {
                IndexSearcher searcher = new IndexSearcher(reader);
                TopDocs docs = searcher.search(q, hitsPerPage);
                ScoreDoc[] hits = docs.scoreDocs;
                
                SaveResult(hits, searcher);
                
                isFound = hits.length > 0;
            }
        } catch (ParseException | IOException ex) {
            //Logger.getLogger(LuceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        hasSeacrh = true;
        return isFound;
    }
    
    private void SaveResult(ScoreDoc[] hits, IndexSearcher searcher) throws IOException{
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            result.add(new Pair(Integer.parseInt(d.get("index")), d.get("question")));
        }
    }
        
    /**
     * Menampilkan data yang ditemukan pada pencarian sebelumnya.
     */
    public List<Integer> ShowResult(){
        
        List<Integer> idQuestion = new ArrayList<>();
        result.forEach((res) -> 
        {
            //String value = res.getValue();
            Integer key = res.getKey();
            //System.out.println(key + " " + value);
            idQuestion.add(key);
        }
        );
        
        hasSeacrh = true;
        
        return idQuestion;
    }
    
    private void addDoc(IndexWriter w, String question, int index) throws IOException {
        Document doc = new Document();
        
        doc.add(new TextField("question", question, Field.Store.YES));
        doc.add(new StringField("index", String.valueOf(index), Field.Store.YES));
        
        w.addDocument(doc);
    }
}
