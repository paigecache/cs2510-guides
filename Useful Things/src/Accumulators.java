class Accumulators {
  // accumulators and accumulator-like things are used to pass information between method calls
  // you can't do field-of-field access, but you can call a method on an object parameter and pass
  // in the info you would otherwise be unable to access
  
  // this method gets called on this, passed in this.youb, and carries the yob of the previous generation
  // every time it gets called
  boolean wellFormedHelp(int childYob) {
     return this.yob <= childYob &&
            this.mom.wellFormedHelp(this.yob) &&
            this.dad.wellFormedHelp(this.yob);
  }
  
  // this method uses gen shrinking to determine where in a list to wind up stopping
  public IAT youngestAncInGen(int gen) {
    if (gen == 0) {
        return this;
    }
    else {
        return this.mom.youngestAncInGen(gen - 1).youngerIAT(this.dad.youngestAncInGen(gen - 1));
    }
  }
  
  // another example of reducing a counter to determine output, also doing so switching between classes
  // In interface IPaint, class Combo; operation is an IMixture
  public String mixingFormula(int depth) {
    if(depth <= 0) {
      return this.name;
    }
    else {
      return this.operation.mixFormula(depth); 
    }
  // In interface IMixture (field of Combo), class Brighten; paintColor is an IPaint
  public String mixFormula(int depth) {
    if(depth <= 0) {
      return "brighten(" + this.paintColor.getName() + ")";
    }
    else {
      return "brighten(" + this.paintColor.mixingFormula(depth - 1) + ")";   
    }
  }
}