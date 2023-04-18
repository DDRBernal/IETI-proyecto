import React, { useState, useEffect } from 'react';
import Axios from 'axios';

interface Component {
  id: number;
  nombre: string;
}

function App() {
  const [data, setData] = useState<Component[]>([]);

  useEffect(() => {
    Axios.get('http://localhost:8080/v1/componente/getAllComponents')
      .then(response => setData(response.data))
      .catch(error => console.log(error));
  }, []);

  return (
    <div>
      <h1>Data from Spring Boot API:</h1>
      <ul>
        {data.map(item => (
          <li key={item.id}>{item.nombre}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
