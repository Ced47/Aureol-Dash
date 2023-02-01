import React, { useEffect, useState } from 'react'
//import { Country,State,City } from 'country-state-city'
import '../CSS/home.css'

export default function Home() {
  // widget horloge 
  const [time,setTime]=useState("")
  const [date,setDate]=useState("")

  function format(value) {
    if (value<10) {
      return 0;
    } else {
      return "";
    }
  }

  useEffect(() => {
    const timerID= setInterval(() => tick(), 1000)

    return function cleanup() {
      clearInterval(timerID)
    }
  })

  function tick() {
    // time
    const d=new Date();
    const h=d.getHours();
    const m=d.getMinutes();
    const s=d.getSeconds();
    const j=d.getDay();
    const mo=d.getMonth()+1;
    const y=d.getFullYear();

    // format
    setTime(format(h) + h +":" + format(m) + m + ":" +format(s) + s);

    setDate(j + ":" + mo + ":" + y);
  }


  // widget calculator 
  const [val, setVal]=useState("")

  const backSpace = () => {
    try {
      setVal(val.slice(0, 0))
    } catch (error) {
      setVal("")
    }
  }

  const calculate = () => {
    try {
      setVal(eval(val));
    } catch (error) {
      setVal ("Error")
    }
  }

  // widget weather
  const [search,setSearch]=useState("paris");
  const [data, setData]= useState([]);
  const [input,setInput]=useState("")

  let mounted=true;

  useEffect(() => {
    const fetchWeather = async () => {

      const response=await fetch('https://api.openweathermap.org/data/2.5/weather?q='+search+'&appid=6f015419bda90dc7a00436f86d0506ce');
      if (mounted) {
        setData(await response.json());
        console.log(data);
      } else {
       return () => {
        mounted=false;
       } 
      }
    }
    fetchWeather();
  }, []);



  return (
    <section>
        <div className='dash'>
          <div className='service'>
            <div className='title_div'>
              <h3>MY DASHBOARD</h3>
            </div>

            <div className='ba'>
              <h4>Horloge</h4>
            </div>

            <div className='ba'>
              <h4>Météo</h4>
            </div>

            <div className='ba'>
              <h4>Calculatrice</h4>
            </div>

            <div className='ba'>
              <h4>Reedit</h4>
            </div>
          </div>
        </div>

        <div className='board'>
          <div className='horloge'>
            <div className='ecran'>
              <h2 className='time'>{time}</h2>
            </div>
          </div>

          <div className='calculator'>
            <div className='cal'>
              <input type="text" value={val} onChange = {(e) => setVal(e.target.value)} />

              <div className='row'>
                <button className='' value="1" onClick={(e) => setVal(val + e.target.value)}>1</button>
                <button className='' value="2" onClick={(e) => setVal(val + e.target.value)}>2</button>
                <button className='' value="3" onClick={(e) => setVal(val + e.target.value)}>3</button>
                <button className='' value="AC" onClick={() => backSpace()}>AC</button>
              </div>

              <div className='row'>
                <button className='' value="4" onClick={(e) => setVal(val + e.target.value)}>4</button>
                <button className='' value="5" onClick={(e) => setVal(val + e.target.value)}>5</button>
                <button className='' value="6" onClick={(e) => setVal(val + e.target.value)}>6</button>
                <button className='' value="+" onClick={(e) => setVal(val + e.target.value)}>+</button>
              </div>

              <div className='row'>
                <button className='' value="7" onClick={(e) => setVal(val + e.target.value)}>7</button>
                <button className='' value="8" onClick={(e) => setVal(val + e.target.value)}>8</button>
                <button className='' value="9" onClick={(e) => setVal(val + e.target.value)}>9</button>
                <button className='' value="*" onClick={(e) => setVal(val + e.target.value)}>x</button>
              </div>

              <div className='row'>
                <button className='' value="." onClick={(e) => setVal(val + e.target.value)}>.</button>
                <button className='' value="0" onClick={(e) => setVal(val + e.target.value)}>0</button>
                <button className='' value="=" onClick={() => calculate()}>=</button>
                <button className='' value="/" onClick={(e) => setVal(val + e.target.value)}>/</button>
              </div>
            </div>
          </div>

          <div className='weather'>
            <div className='weat'>
              <input type="search" value={val} onChange = {(e) => setVal(e.target.value)} />
              <button type="submit" className='btn'>🔍</button>
            </div>

            <div className='field'>
              <div className='city'><h2>{data.name}</h2></div>
              <div className='met'><h2>{(data.main.temp -273.15).toFixed(2)} °C</h2></div>
            </div>
          </div>
        </div>
    </section>
  )
}
