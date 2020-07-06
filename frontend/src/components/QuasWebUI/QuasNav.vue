<template>
    <nav>
        <div class="quas-nav-bar">
            <ul>
                <li>
                    <img src="favicon.ico" width="32" height="32">
                    <a href="#" class="quas-nav-link">Questionnaire Analysis System</a>
                </li>
            </ul>
            <ul class="quas-nav-list">
                <li v-for="(label, index) in labels" :key="index" class="quas-nav-item">
                    <a class="quas-nav-link" :href="links[index]">{{label}}</a>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "QuasNav",
        props: {
            labels: {
                type: Array,
                required: true
            },
            links: {
                type: Array,
                required: true
            },
            active_index: {
                type: Number,
                default: 0
            }
        }
    }
</script>

<style scoped>

</style>