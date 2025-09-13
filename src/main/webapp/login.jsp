<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center min-h-screen">

<div class="bg-white shadow-2xl rounded-2xl w-full max-w-md p-8 animate-fadeIn">
    <h2 class="text-3xl font-bold text-center text-indigo-700 mb-6">Welcome 👋</h2>

    <!-- Existing login form -->
    <form action="login" method="post" class="space-y-5">
            <!-- Username -->
            <div>
                <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
                <input type="text" id="username" name="username" required
                       class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg 
                              focus:ring-2 focus:ring-indigo-400 focus:outline-none">
            </div>

            <!-- Password -->
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" required
                       class="w-full mt-1 px-4 py-2 border border-gray-300 rounded-lg 
                              focus:ring-2 focus:ring-indigo-400 focus:outline-none">
            </div>

        <!-- Login button -->
        <button type="submit"
                class="w-full py-2 bg-indigo-500 hover:bg-indigo-600 rounded-lg text-white font-semibold transition duration-300">
            Login
        </button>

        <!-- Sign Up Button -->
        <a href="register.jsp"
            class="block w-full text-center bg-gray-200 hover:bg-gray-300 text-gray-800 py-2 px-4 
                    rounded-lg font-semibold transition duration-200">
            Sign Up
        </a>
    </form>

    <% String error = (String) request.getAttribute("errorMessage"); %>
    <% if (error != null) { %>
        <div class="mt-4 p-3 rounded-lg bg-red-100 text-red-600 text-center text-sm">
            <%= error %>
        </div>
    <% } %>
</div>

<!-- Simple fade animation -->
<style>
    .animate-fadeIn {
        animation: fadeIn 0.7s ease-in-out;
    }
    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(10px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>

</body>
</html>