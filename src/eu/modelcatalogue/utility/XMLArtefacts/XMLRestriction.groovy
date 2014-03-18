package eu.modelcatalogue.utility.XMLArtefacts

/**
 * Created by davidmilward on 26/02/2014.
 */
class XMLRestriction extends XMLComplexElement{


    String baseName
    XMLComplexType baseComponent
    Integer max
    Integer min
    Integer minlen
    Integer maxlen
    Integer minInclusive
    Integer maxInclusive
    Integer minExclusive
    Integer maxExclusive
    Integer totalDigits
    Integer fractionDigits
    List enumerations
    String pattern
    String whitespace
    ArrayList<String> alRestrictions = new ArrayList<String>()



    String getBaseName() {
        return baseName
    }

    void setBaseName(String baseName) {
        this.baseName = baseName
    }

    XMLComplexType getBaseComponent() {
        return baseComponent
    }

    void setBaseComponent(XMLComplexType baseComponent) {
        this.baseComponent = baseComponent
    }

    Integer getMax() {
        return max
    }

    void setMax(Integer max) {
        this.max = max
        alRestrictions.add("max")
    }

    Integer getMin() {
        return min
    }

    void setMin(Integer min) {
        this.min = min
        alRestrictions.add("min")
    }

    Integer getMinlen() {
        return minlen
    }

    void setMinlen(Integer minlen) {
        this.minlen = minlen
        alRestrictions.add("minlen")
    }

    Integer getMaxlen() {
        return maxlen
    }

    void setMaxlen(Integer maxlen) {
        this.maxlen = maxlen
        alRestrictions.add("maxlen")
    }

    Integer getMinInclusive() {
        return minInclusive
    }

    void setMinInclusive(Integer minInclusive) {
        this.minInclusive = minInclusive
        alRestrictions.add("minInclusive")
    }

    Integer getMaxInclusive() {
        return maxInclusive
    }

    void setMaxInclusive(Integer maxInclusive) {
        this.maxInclusive = maxInclusive
        alRestrictions.add("maxInclusive")
    }

    Integer getMinExclusive() {
        return minExclusive
    }

    void setMinExclusive(Integer minExclusive) {
        this.minExclusive = minExclusive
        alRestrictions.add("minExclusive")
    }

    Integer getMaxExclusive() {
        return maxExclusive
    }

    void setMaxExclusive(Integer maxExclusive) {
        this.maxExclusive = maxExclusive
        alRestrictions.add("maxExclusive")
    }

    Integer getTotalDigits() {
        return totalDigits
    }

    void setTotalDigits(Integer totalDigits) {
        this.totalDigits = totalDigits
        alRestrictions.add("totalDigits")
    }

    Integer getFractionDigits() {
        return fractionDigits
    }

    void setFractionDigits(Integer fractionDigits) {
        this.fractionDigits = fractionDigits
        alRestrictions.add("fractionDigits")
    }

    boolean validateContent(String sContent){
        Boolean bResult = true
        Iterator<?> restIT = alRestrictions.iterator();
        while(restIT.hasNext()) {
            @SuppressWarnings("rawtypes")
            String sRest = (String)restIT.next();
            switch (sRest) {
                case "max":
                    if(sContent.toInteger() <= this.max){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break
                case "min":
                    if(sContent.toInteger() > this.min){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break
                case "maxExclusive":
                    if(sContent.toInteger() < this.maxExclusive){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "minExclusive":
                    if(sContent.toInteger() > this.minExclusive){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "maxInclusive":
                    if(sContent.toInteger() <= this.maxInclusive){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "minInclusive":
                    if(sContent.toInteger() >= this.minInclusive){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "maxlen":
                    if(sContent.length() <= this.maxlen){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "minlen":
                    if(sContent.length() >= this.minlen){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "totalDigits":
                    if(getTotalDigits(sContent) == this.totalDigits){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "fractionDigits":
                    if(getFractionDigits(sContent) == this.fractionDigits){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                    break;
                case "pattern":
                    if(checkPattern(sContent)){
                        bResult = bResult.and(true)
                    }else{
                        bResult = bResult.and(false)
                    }
                /* @todo : add in whitespace element from scratch
                    case "whitespace":
                    if(this.whitespace == "replace"){
                        bResult = bResult.and(true)
                    }else (this.whitespace == "collapse"){
                        bResult = bResult.and(false)
                    }else{

                    }
                    break;*/

                default:
                    //???
                    break;

            }
            return bResult

        }


    }

    boolean checkPattern(String pattern, String content){
        return pattern =~ content
    }

    int getTotalDigits(String digits){
        int ind = digits.indexOf('.')
        String newDigits = digits.substring(0, ind)
        return newDigits.length()
    }

    int getFractionDigits(String digits){
        int ind = digits.indexOf('.')
        String newDigits = digits.substring(ind)
        return newDigits.length()
    }


}
