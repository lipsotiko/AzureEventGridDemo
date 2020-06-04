<template>
  <div class="app">
    <div class="container">
      <div class="nav">
        <router-link to="/">Service A</router-link> |
        <router-link to="/service-b">Service B</router-link>
      </div>
      <span
        >**Note: To observe functionality, both services must be open in
        different browser windows prior to sending messages.</span
      >
      <router-view />
    </div>
  </div>
</template>

<style lang="scss">
.app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-left: 22px;
}

.nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}

.container {
  width: 1040px;
  margin: auto;
}
</style>
