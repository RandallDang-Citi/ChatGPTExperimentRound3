import React from "react";

// "key": "mode",
//           "type": "radio",
//           "label": "Mode of Payment",
//           "options": [
//             {
//               "label": "Bank",
//               "value": "Bank"
//             },
//             {
//               "label": "other",
//               "value": "other"
//             }
//           ],
//           "order":
// create a function return a react component, the component will contains two divs, each div has key,order, type, label, and  can be clicked,
// when click the div, the function will get the div's key, label, order, type, and return a object contain key, label and input value 
function RadioSelect(props) {
  // const { key, type, label, order, options, onChrulenge } = props;

  const { onChrulenge, setBtnDisabled } = props;
  const { key, type, label, order, rule, options} = props.data;

  function onRadioClick(option) {
    console.log("onRadioClick", option)
    
    onChrulenge({ key, option, label, order,rule, type });
  }

  return (
    <div className="RadioSelect">
      <label htmlFor={options.value}>{options.label}</label>
      {
        options.map((option, index) => {
          return (
            <div key={index} onClick={ ()=>{onRadioClick(option)} } > 
              <div>{option.label}</div>

            </div>
          )
        })
      }
 
    </div>
  );
}

export default RadioSelect