package com.dedalus.patients.mapper.impl;
   

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Component; 
import com.dedalus.patients.dto.PatientResponseDto;
import com.dedalus.patients.mapper.PatientMapper;
import com.dedalus.patients.utils.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode; 

@Component
public class PatientMapperImpl implements PatientMapper  {

	@Override
	public PatientResponseDto patientDtotoPatientResponseDto(String response) { 
		ObjectMapper mapper = new ObjectMapper(); 
		JsonNode root = null;
		try {
			root = (JsonNode)mapper.readTree(response);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		return response != null ? PatientResponseDto.builder()
				.id(nodeValue(Constants.NODE_ID, root))
				.name(getName(root))
				.surname(getJsonNodes(root, Constants.NODE_NAME, Constants.NODE_FAMILY))
				.nhc(getJsonNodes(root, Constants.NODE_IDENTIFIER, Constants.NODE_VALUE))
 				.gender(nodeValue(Constants.NODE_GENDER, root))
 				.birthDate(getLocalDateNode(root, Constants.NODE_BIRTHDATE ))
				.build() : null; 
	}

	private String getName(JsonNode jsonNode) {
		JsonNode jsonName = jsonNode.get(Constants.NODE_NAME);
		String name = "";
		if(jsonName != null && jsonName.isArray()) {
			ArrayNode nodeArray = (ArrayNode) jsonName;
			for (JsonNode element : nodeArray) {  
				JsonNode item = element.get(Constants.NODE_GIVEN);
				if(item != null && item.isArray()) {
					ArrayNode givenArray = (ArrayNode) item;
					for (JsonNode given : givenArray) {  
						name += given.asText();
					}
				}
            }
		}
		return name;
	}
	private String getJsonNodes(JsonNode jsonNode, String principalNode, String secondaryNode) {
		JsonNode jsonName = jsonNode.get(principalNode); 
		if(jsonName != null && jsonName.isArray()) {
			ArrayNode nodeArray = (ArrayNode) jsonName;
			for (JsonNode element : nodeArray) {  
				JsonNode item = element.get(secondaryNode);
				return item != null ? item.asText() : null;
            }
		}
		return null;
	} 
	private  String nodeValue(String fieldName, JsonNode node) {
		if(node != null && fieldName != null) {
			JsonNode result = node.get(fieldName);
			if(result != null) {
				
				String value = result.asText();
				return value;
			}
			return null;
			
		}
		return null;
	}
	private LocalDate getLocalDateNode(JsonNode node, String nameField) {
		String date = nodeValue(nameField,  node);
        return LocalDate.parse(date);

		
	}

}
