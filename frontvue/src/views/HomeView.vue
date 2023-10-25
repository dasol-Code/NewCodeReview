<template>
  <v-container>
    <div class="tag">
      <h1><small></small>CODE REVIEW TEST<small></small></h1>
    </div>
  </v-container>
</template>

<script>
export default {};
</script>
<style>
.box {
}
/* .box {
  letter-spacing: var(--letter-spacing);
  padding-left: var(--letter-spacing);
  box-shadow: 0.03em 0.03em 0.05em rgba(0, 0, 0, 0.2),
    0.5em 0.5em 0 var(--shadow-color), 0.51em 0.51em 0 var(--shadow-color),
    0.53em 0.53em 0 var(--shadow-color), 0.53em 0.54em 0 var(--shadow-color),
    0.55em 0.55em 0 var(--shadow-color), 0.55em 0.56em 0 var(--shadow-color),
    0.55em 0.57em 0 var(--shadow-color);
  --shadow-color: #a4a1a0;
} */
.tag h1 {
  --letter-spacing: 0.5vw;
  width: 100%;
  text-align: center;
  margin: 0;
  font-size: 8vw;
  line-height: 3;
  font-family: "Teko", sans-serif;
  letter-spacing: var(--letter-spacing);
  padding-left: var(--letter-spacing);
  color: #fffbe6;
  text-shadow: 0.03em 0.03em 0.05em rgba(0, 0, 0, 0.2),
    0.01em 0.01em 0 var(--shadow-color), 0.02em 0.02em 0 var(--shadow-color),
    0.03em 0.03em 0 var(--shadow-color), 0.03em 0.04em 0 var(--shadow-color),
    0.05em 0.05em 0 var(--shadow-color), 0.05em 0.06em 0 var(--shadow-color),
    0.05em 0.07em 0 var(--shadow-color);
  --shadow-color: #63c8aa;
}
.tag small {
  font-size: 0.5em;
  vertical-align: top;
}

* {
  box-sizing: border-box;
}

.container {
  padding-left: 15px;
  padding-right: 15px;
  margin-left: auto;
  margin-right: auto;
}
</style>
