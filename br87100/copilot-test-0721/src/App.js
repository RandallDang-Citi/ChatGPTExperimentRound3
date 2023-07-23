import React, { useState, useEffect, useCallback } from "react";
import { Button } from "@mui/material";

import "./App.css";

import TextBox from "./components/TextBox";
import RadioSelect from "./components/RadioSelect";
const metaData = require('./metadata.json');
const reqData = []
function App() {


  const [btnDisabled, setBtnDisabled] = useState(true)
  const [reuiqredData, setRequiredData] = useState([])
  // let reuiqredData = 0

  useEffect(() => {
    // forEach the data of metaData's modal, if the data's components' data.rule.required is true, push the data to the reuiqredData
    const requredLength = []
    metaData.modal.forEach((item) => {
      item.components.forEach((item) => {
        if (item.rule !== undefined) {
          if (item.rule.required) {

            requredLength.push(item)
          }
        }
      })
    })
    console.log(requredLength)
    setRequiredData(requredLength)
    // reuiqredData = requredLength
    // console.log(requredLength)
  }, [])

  const checkRequired = () => {
    // forEach the reuiqredData and reqData, if the data of reuiqredData's key in reqData, return true, else return false
 
    console.log(reuiqredData,reqData)
    let required = true
    const reqKeys = reqData.map((item) => item.key)
    reuiqredData.forEach((requireItem) => {
   

      reqData.forEach((item) => {
        // if reqData's key does not in the reqKeys, return false
        if (reqKeys.indexOf(requireItem.key) === -1) {
          required = false
        }
      })
    })
    
    return required

  }

  const onChrulenge = (data) => {
    // map the data to the reqData, if the data is already in the reqData, replace the data in the reqData
    // if the data is not in the reqData, push the data to the reqData
    // if the data is empty, remove the data from the reqData
    // if the data is not empty, push the data to the reqData
    // if the data is not in the reqData, push the data to the reqData
    reqData.forEach((item) => {
      if (item.key === data.key) {
        if (!data.value) {
          
        } else {
          item.value = data.value
        }

      } else {
        validBtnDisabled()
      }
    }
    )
    reqData.push(data)

    console.log(reqData)
  }

  const validBtnDisabled = () => {
    let disabled = false
    const requireOk = checkRequired()
    if (!requireOk) {
      disabled = true
    }
    reqData.forEach((item) => {
      if (item.rule !== undefined) {
        if (item.rule.maxLength ) {
          if (item.value.length > item.rule.maxLength) {
            
            disabled = true
          }
        }
      }
    })
    setBtnDisabled(disabled)
  }

  

  // const renderSelComponents

  // create a function renderSelComponents, the function will return a react component based on the metaData's modal, 

  // forEach the each item of the modal, if the item's type is "textbox" return TextBox, if item's type is "radio" return RadioSelect
  // the function will be called in the return of the App function

  const renderSelComponents = (meComponents) => {
    const components = []
    meComponents.forEach((item) => {
      if (item.type === "textbox") {
        components.push(<TextBox data={item} onChrulenge={onChrulenge} setBtnDisabled={setBtnDisabled} key={item.key} />)
      } else if (item.type === "radio") {
        components.push(<RadioSelect data={item} onChrulenge={onChrulenge} setBtnDisabled={setBtnDisabled} key={item.key}/>)
      }
    })
    return components
  }

  //handleSubmit function will valid the data of reqData, if the data is valid, the btnDisabled will be true
  // if the value of data of reqData is match to it's rule, the data is valid, if the value of data of reqData is not match to it's rule, the data is not valid

  const handleSubmit = () => {
    //forEach the data of reqData,  if the data is match to it's rule, the btnDisabled will be true
    // if the data is not match to it's rule, the btnDisabled will be false
    console.log(reqData)
   

  }

  return (
    <div id="meta-data">
    {
      metaData.modal.sort(item => item.order).map((item) => {
        return (
          <div className="flex p-10" key={item.order + item.section}>
            <div>{item.section}:</div>
            <div>{renderSelComponents(item.components)}</div>
          </div>
        )
      })
    }

    <div>
      <Button disabled={btnDisabled} className="bg-blue-500 font-bold py-2 px-4 rounded" onClick={handleSubmit}>Submit</Button>
    </div>
    </div>
  );
}

export default App;
