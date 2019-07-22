module.exports = function codeSmells() {
  const smellsModel = [
    {
      id: 1, type: 'ThatThis', title: 'That or This', icon: 'exchange-alt',
    },
    {
      id: 2, type: 'ConCat', title: 'ConCat', icon: 'cat',
    },
    {
      id: 3, type: 'ExtremeChain', title: 'Extreme Chain', icon: 'link',
    },
    {
      id: 4, type: 'AnonymousFunction', title: 'Anonymous Function', icon: 'mask',
    },
    {
      id: 5, type: 'Equality', title: 'Equality', icon: 'equals',
    },
    {
      id: 6, type: 'BrokenPromise', title: 'Broken Promise', icon: 'heart-broken',
    },
    {
      id: 7, type: 'MultipleReturn', title: 'Multiple Return', icon: 'undo',
    },
  ];

  return smellsModel;
};
