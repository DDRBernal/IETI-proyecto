import React, { useState, useEffect } from 'react';


function App() {
  

  

  return (
    <div>
      <h1>Data from Spring Boot API:</h1>
      <ul>
        {data.map(item => (
          <li key={item.id}>{item.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
