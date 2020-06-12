# WelcomeMessage
Customize your servers welcome message with this simple plugin. It also allows you to add server variables to your welcome message, such as online users, online administrators, etc.

## 💡 How does it work?

If a client joins your server, this plugin sends him a custom welcome message, which can be configured in the plugins config.

## 🚀 Gettings started

Just download the latest release that's compatible with your TSQPF version and copy it into its plugin directory. After you've done that, either reload or restart your framework instance in order to get it loaded and initiated.

## ⚙️ Configuration

Here's a list of all config keys, value datatypes and a description:

KEY | DATATYPE | DESCRIPTION

- **messageWelcome** : [String] Defines the welcome message.
- **messageWelcomePoke** : [String] Defines the poke message on client join (leave empty to disable feature).
- **messageFirstJoin** : [String] Defines the message sent to client on first join (leave empty to disable feature).
- **messageFirstJoinPoke** : [String] Defines the poke message sent to client on first join (leave empty to disable feature).
- **staffGroups** : [Integer] Staff groups of your server (separated by comma with no whitespaces).

## ⚙️ Message Placeholder

Here's a list of all placeholders that can be used in the welcome message:

KEY | DESCRIPTION

- **onlineUsers** : Total count of online users, minus the framework.
- **onlineOtherUsers** : Count of other online users, minus own client and framework.
- **totalUniqueUsers** : Count of unique users in the database.
- **servergroupCount** : Count of server groups.
- **channelCount** : Count of channels.
- **channelgroupCount** : Count of channel groups.
- **onlineStaffCount** : Count of online staff members.
- **bannedClientCount** : Count of banned clients.
- **lastIssuedBanDate** : Date of last issues ban.
- **clientNickname** : The clients nickname.


## 📁 Directory Tree

AntiAFK/<br>
└── plugin.conf<br>

## 📜 Vortexdata Certification

This plugin is developed by VortexdataNET for the Teamspeak Query Plugin Framework. Every release is being tested for any bugs, its performance or security issues. You are free to use, modify or redistribute the plugin.
