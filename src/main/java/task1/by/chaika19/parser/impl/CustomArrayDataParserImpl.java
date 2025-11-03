package task1.by.chaika19.parser.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.parser.CustomArrayDataParser;

public class CustomArrayDataParserImpl implements CustomArrayDataParser {
    private static final Logger logger = LogManager.getLogger(CustomArrayDataParserImpl.class);

    @Override
    public int[] parseLine(String line) throws CustomArrayException {
        if (line != null && !line.isBlank()) {
            String[] stringTokens = line.trim().split(SPACE_DELIMITER_REGEX);

            int[] resultArray = new int[stringTokens.length];

            for (int i = 0; i < stringTokens.length; i++) {
                String strToken = stringTokens[i];

                try {
                    resultArray[i] = Integer.parseInt(strToken);
                } catch (NumberFormatException e) {
                    logger.error("Invalid integer format during parsing:  {}", strToken, e);
                    throw new CustomArrayException("Invalid integer format during parsing: " + strToken, e);
                }
            }

            logger.info("Successfully parsed {} numbers from line.", resultArray.length);
            return resultArray;
        } else {
            logger.warn("Cannot parse array from empty or null line {}", line);
            throw new CustomArrayException("Cannot parse array from empty or null line");
        }
    }
}
