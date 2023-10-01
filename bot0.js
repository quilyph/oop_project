const { Telegraf } = require('telegraf');
const token = '6443515200:AAFcRhpl7ljLRY7VjdATMmoPGAO_7GYJwIw';
const bot = new Telegraf(token, {polling: true});

bot.start((ctx) => ctx.reply('hi'));
bot.help((ctx) => ctx.reply('I am echo Bot. tell me something and i will repeat it'));
bot.on('message', (ctx) => ctx.reply(ctx.message.text));
bot.hears('echo', (ctx) => ctx.reply('echo echo echo...'));
bot.launch();

process.once('SIGINT', () => bot.stop('SIGINT'));
process.once('SIGTERM', () => bot.stop('SIGTERM'));