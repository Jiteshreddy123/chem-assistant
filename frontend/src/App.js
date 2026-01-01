import { useState } from "react";

function App() {
  const [message, setMessage] = useState("");

  const checkBackend = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/health");
      const text = await res.text();
      setMessage(text);
    } catch (err) {
      setMessage("Cannot reach backend");
    }
  };

  return (
    <div style={{ padding: 30 }}>
      <h2>Chem Assistant</h2>

      <button onClick={checkBackend}>
        Check Backend Connection
      </button>

      <p>{message}</p>
    </div>
  );
}

export default App;
