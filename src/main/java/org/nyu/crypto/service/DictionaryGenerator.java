package org.nyu.crypto.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.nyu.crypto.dto.Dictionary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;



@Service
public class DictionaryGenerator {

    private ObjectMapper objectMapper;

    public Dictionary generateDictionaryDto() {
        Dictionary dictionary = new Dictionary();
        objectMapper = new ObjectMapper();
        try {
            File dictionaryFile = new ClassPathResource("dictionary.json").getFile();
            dictionary = objectMapper.readValue(dictionaryFile, Dictionary.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;

    }

    public Dictionary generateDictionaryDto(int dictionaryLength){
        Dictionary dictionary = generateDictionaryDto();

        ArrayList<String> shuffledWords =  new ArrayList<String>(Arrays.asList(dictionary.getWords()));
        Collections.shuffle(shuffledWords);

        ArrayList<String> shuffledWordsSubList=new ArrayList<String>(shuffledWords.subList(0,dictionaryLength));

        dictionary.setWords(shuffledWordsSubList.toArray(new String[shuffledWordsSubList.size()]));

        return dictionary;
    }
}
