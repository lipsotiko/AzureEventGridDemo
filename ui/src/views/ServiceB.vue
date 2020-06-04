<template>
  <div>
    <h1>Service B</h1>
    <span v-if="!connected">establishing websocket connection...</span>
    <div v-else>
      <span v-if="messages.length === 0">Use Service A to send a message and it will appear here.</span>
      <section v-for="m in messages" :key="m.id">
        <summary
          >{{ m.subject }}:{{ m.eventType }}:{{ m.id }}:v{{ m.dataVersion }}:{{
            m.eventTime
          }}</summary
        >
        <span>{{ m.data }}</span>
      </section>
    </div>
  </div>
</template>
<script>
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default {
  name: 'ServiceB',
  data: () => ({
    socket: null,
    stompClient: null,
    connected: false,
    messages: []
  }),
  created () {
    this.socket = new SockJS('/websocket')
    this.stompClient = Stomp.over(this.socket)
    this.stompClient.connect(
      {},
      frame => {
        this.connected = true
        this.stompClient.subscribe('/topic/messages', tick => {
          this.messages.push(JSON.parse(tick.body))
        })
      },
      error => {
        console.log(error)
        this.connected = false
      }
    )
  }
}
</script>
<style scoped>
summary {
  margin: 12px;
  font-weight: bold;
}

summary + span {
  margin: 24px;
}
</style>
