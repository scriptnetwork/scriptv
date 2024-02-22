# scriptv 
P2P Crypto based on cooperative consensus

# INSTRUCTIONS

CORE0. BUILD. STEPS
===================

cd core0/us
bin/configure

[follow on-screen further instructions for backend gov and wallet, client console programs, java sdk and android app ]


CORE1. BUILD. STEPS
===================
r2r (wallet plugins)
cd core1/us/trader
bin/configure
make -j8


SINGLE DEV
==========
1.- Download and uncompress the job file using zstd
    wget ${devjob_puburl}/${gitdir}.zst
    tar -I zstd -xf ${gitdir}.zst
    Produced directory: ${gitdir}

2.- Clone a working copy
    git clone $gitdir

3.- Perform your work in ${workdir}, commit and push changes

4.- Compress the repository dir
    tar -I zstd -cz ${gitdir}.zst ${gitdir}

5.- Send over

DEV TEAM LEAD
=============
Setup
-----
In your host machine M do:
 adduser dev
 su dev
 ssh-keygen
 #request devs to send you their ssh public keys (.ssh/id_rsa.pub)
 #append devs rsa public keys in /home/dev/.ssh/authorized_keys

 1.- Download and uncompress the job file using zstd
    wget ${devjob_puburl}/${gitdir}.zst
    tar -I zstd -xf ${gitdir}.zst
    Produced directory: ${gitdir}
    mv ${gitdir} /home/dev
    chown dev.dev. /home/dev/${gitdir} -R

 2.- Publish your clone command:
     git clone dev@M:~${gitdir}


 3.- Merge feature branch ftb
     git checkout master
     git pull origin ftb
     git merge ftb

DEV
===

 1.- Submit your ssh public key to team lead.
     content of file ${HOME}/.ssh/id_rsa.pub

 2.- git clone dev@M:~${gitdir}

 3.- cd ${workdir}
     #check current branch, should be master
     git branch

 4.- create a feature branch mybranch of master
     git checkout -b mybranch

 5.- commit your work

 6.- Pull-merge upstream changes
     git pull origin master
     git merge master


FILES - GENERAL DESCRIPTION
===========================

Core0 [core0/]
   Public System
     BACKEND
       * [us/gov/] - kernel 
     HMI - Human-Machine Interfaces:
       * [us/govx/] - Console RPC client.
   Private System
     BACKEND
       * [us/wallet/] - kernel
       * [sdk] - RPC client libraries in various languages (java, c#, ...)
     HMI - Human-Machine Interfaces:
       * [us/walletx/] - Console RPC client. (written in C++)
       * [us/walletj/] - Console RPC client. (written in Java)
       * [us/android/] - Compatible with Android Studio

Core1 [core1/]
   -brands - UX, Look&Feel
       * [brands/]
   -r2r -Role-to-Role protocols
       * [r2r/]
   -cbs -continous build system
       * [bin/cbs]
Core2
