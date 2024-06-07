<!DOCTYPE html>
<html>
<head>
    <title>Update Record</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        form {
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
        }
        label, input, textarea {
            display: block;
            margin-bottom: 10px;
        }
        label {
            font-weight: bold;
        }
        input, textarea {
            width: calc(100% - 16px);
            padding: 8px;
            box-sizing: border-box;
        }
        button {
            width: calc(100% - 16px);
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <form action="update" method="POST">
        <h2>Update Record</h2>
        <label for="id">ID</label>
        <input type="number" id="id" name="id" required>

        <label for="arrivalDate">Arrival Date</label>
        <input type="text" id="arrivalDate" name="arrivalDate">

        <label for="companyName">Company Name</label>
        <input type="text" id="companyName" name="companyName" >

        <label for="nextDate">Next Date</label>
        <input type="text" id="nextDate" name="nextDate">

        <label for="status">Status</label>
        <input type="text" id="status" name="status">

        <label for="tag">Tag</label>
        <input type="number" id="tag" name="tag">

        <label for="role">Role</label>
        <input type="text" id="role" name="role">

        <label for="description">Description</label>
        <textarea id="description" name="description"></textarea>

        <button type="submit">Update</button>
    </form>
</body>
</html>
