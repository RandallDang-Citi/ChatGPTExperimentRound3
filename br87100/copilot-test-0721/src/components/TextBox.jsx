import React from "react";


// function TextBox(props) {
//   const { key, type, label, order, onChrulenge } = props;
 

//   return (
//     <div className="TextBox">
//       TextBox
//     </div>
//   );

 
// }

// create a function return a react component, with label, input
function TextBox(props) {
  const { onChrulenge, setBtnDisabled } = props;
  const { key, type, label, order, rule } = props.data;
  //onInputChange functon will get input value, and return a object contain key, label and input value
  function onInputChange(event) {
    const { name, value } = event.target;
    console.log("onInputChange", name, value)

    if(value){
      onChrulenge({ key, value, label, order, rule, type });
    }
   
  }

  // if rule.required is true, the input is required, * will be added after the label


  return (
    <div className="TextBox">
      <label className="required" >{rule && rule.required ? "*" : ""}</label>
      <label htmlFor={key}>{label}</label>
      <input
        type={type}
        id={key}
        name={key}
        onChange={onInputChange}
        data-order={order}
        maxLength={rule && rule.maxLength ? rule.maxLength : ""}
      />
    </div>
  );
}

export default TextBox;