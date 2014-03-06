module.exports = function(Handlebars) {

this["JST"] = this["JST"] || {};

this["JST"]["./templates/dropdown-option.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression;


  buffer += "<li><a href=\"#\" class='dropdown-option'>";
  if (stack1 = helpers.name) { stack1 = stack1.call(depth0, {hash:{},data:data}); }
  else { stack1 = (depth0 && depth0.name); stack1 = typeof stack1 === functionType ? stack1.call(depth0, {hash:{},data:data}) : stack1; }
  buffer += escapeExpression(stack1)
    + "</a></li>";
  return buffer;
  });

this["JST"]["./templates/dropdown.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression;


  buffer += "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">\r\n<span class=\"dropdown-caption\">"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.current)),stack1 == null || stack1 === false ? stack1 : stack1.name)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\r\n<span class=\"caret\"></span>\r\n</button>\r\n<ul class=\"dropdown-menu\" role=\"menu\">\r\n</ul>";
  return buffer;
  });

this["JST"]["./templates/pod-info.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, stack2, options, functionType="function", escapeExpression=this.escapeExpression, self=this, helperMissing=helpers.helperMissing;

function program1(depth0,data) {
  
  
  return "\r\n    <span class=\"glyphicon glyphicon-tree-conifer\"></span>\r\n    ";
  }

function program3(depth0,data) {
  
  
  return "\r\n    <span class=\"tanker-truck\"></span>\r\n    ";
  }

  buffer += "<div class=\"panel-heading\">\r\n    <h3 class=\"panel-title\">";
  if (stack1 = helpers.name) { stack1 = stack1.call(depth0, {hash:{},data:data}); }
  else { stack1 = (depth0 && depth0.name); stack1 = typeof stack1 === functionType ? stack1.call(depth0, {hash:{},data:data}) : stack1; }
  buffer += escapeExpression(stack1)
    + "</h3>\r\n</div>\r\n<div class=\"panel-body\">\r\n    <p>In the month studied, <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.trips)),stack1 == null || stack1 === false ? stack1 : stack1.saveableCount)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</strong> out of <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.trips)),stack1 == null || stack1 === false ? stack1 : stack1.totalCount)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</strong> trips from the <strong>";
  if (stack2 = helpers.name) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = (depth0 && depth0.name); stack2 = typeof stack2 === functionType ? stack2.call(depth0, {hash:{},data:data}) : stack2; }
  buffer += escapeExpression(stack2)
    + "</strong> pod were less than <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.car)),stack1 == null || stack1 === false ? stack1 : stack1.range)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + " km</strong> long, a percentage of <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.trips)),stack1 == null || stack1 === false ? stack1 : stack1.percentage)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "%</strong>.</p>\r\n    <p>If these trips (excluding ones in vans, utes etc) were instead driven with a <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.car)),stack1 == null || stack1 === false ? stack1 : stack1.name)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</strong> charged with clean energy, <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.co2)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + " kg</strong> of carbon dioxide would be saved!</p>\r\n    <p>That's equivalent to <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.treeCount)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</strong> trees!</p>\r\n    <p>\r\n    ";
  options = {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data};
  stack2 = ((stack1 = helpers.times || (depth0 && depth0.times)),stack1 ? stack1.call(depth0, ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.treeCount), options) : helperMissing.call(depth0, "times", ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.treeCount), options));
  if(stack2 || stack2 === 0) { buffer += stack2; }
  buffer += "\r\n    </p>\r\n    <p>In addition, assuming city mileage levels <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.litres)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</strong> litres of petrol would have been saved. That's <strong>"
    + escapeExpression(((stack1 = ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.tankString)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</strong> full tanks!</p>\r\n    <p>\r\n    ";
  options = {hash:{},inverse:self.noop,fn:self.program(3, program3, data),data:data};
  stack2 = ((stack1 = helpers.times || (depth0 && depth0.times)),stack1 ? stack1.call(depth0, ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.tankCount), options) : helperMissing.call(depth0, "times", ((stack1 = (depth0 && depth0.savings)),stack1 == null || stack1 === false ? stack1 : stack1.tankCount), options));
  if(stack2 || stack2 === 0) { buffer += stack2; }
  buffer += "\r\n    </p>\r\n    <p class=\"text-muted small\">(assuming a tree absorbs <a href=\"http://www.arborenvironmentalalliance.com/carbon-tree-facts.asp\">";
  if (stack2 = helpers.co2PerMonthTree) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = (depth0 && depth0.co2PerMonthTree); stack2 = typeof stack2 === functionType ? stack2.call(depth0, {hash:{},data:data}) : stack2; }
  buffer += escapeExpression(stack2)
    + " kg/month</a> and the <a href=\"http://www.toyota.com.au/yaris/specifications/yr-3-door-hatch-manual\">Yaris' tank size of ";
  if (stack2 = helpers.litresInTank) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = (depth0 && depth0.litresInTank); stack2 = typeof stack2 === functionType ? stack2.call(depth0, {hash:{},data:data}) : stack2; }
  buffer += escapeExpression(stack2)
    + "L</a>)</p>\r\n</div>";
  return buffer;
  });

this["JST"]["./templates/pod-link.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression;


  buffer += "<span class=\"badge\">";
  if (stack1 = helpers.badge) { stack1 = stack1.call(depth0, {hash:{},data:data}); }
  else { stack1 = (depth0 && depth0.badge); stack1 = typeof stack1 === functionType ? stack1.call(depth0, {hash:{},data:data}) : stack1; }
  buffer += escapeExpression(stack1)
    + "</span>";
  if (stack1 = helpers.number) { stack1 = stack1.call(depth0, {hash:{},data:data}); }
  else { stack1 = (depth0 && depth0.number); stack1 = typeof stack1 === functionType ? stack1.call(depth0, {hash:{},data:data}) : stack1; }
  buffer += escapeExpression(stack1)
    + ". ";
  if (stack1 = helpers.name) { stack1 = stack1.call(depth0, {hash:{},data:data}); }
  else { stack1 = (depth0 && depth0.name); stack1 = typeof stack1 === functionType ? stack1.call(depth0, {hash:{},data:data}) : stack1; }
  buffer += escapeExpression(stack1);
  return buffer;
  });

return this["JST"];

};