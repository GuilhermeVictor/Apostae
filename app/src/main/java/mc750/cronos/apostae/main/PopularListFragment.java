package mc750.cronos.apostae.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mc750.cronos.apostae.R;
import mc750.cronos.apostae.library.ObservableScrollViewFragment;
import mc750.cronos.apostae.library.OnCreateListViewListener;
import mc750.cronos.apostae.ui.PopularAdapter;


/**
 * A fragment representing a list of Items.
 * <p>
 * <p>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class PopularListFragment extends Fragment implements ObservableScrollViewFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private RecyclerView mRecyclerView;

    private PopularAdapter mAdapter;

    private PopularViewHolder mViews;

    private OnCreateListViewListener createListViewListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PopularListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_list, container, false);

        // Set the adapter
        mRecyclerView = (RecyclerView) view.findViewById(R.id.popular_recycler_view);
        mRecyclerView.setAdapter(mAdapter);

        if (this.createListViewListener != null)
            this.createListViewListener.onCreateListViewListener(mRecyclerView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViews = new PopularViewHolder(view);
        mViews.initViews(new LinearLayoutManager(getActivity()));
        mAdapter = new PopularAdapter(getActivity());
        mViews.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setOnCreateViewListener(OnCreateListViewListener l) {
        this.createListViewListener = l;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

    private static class PopularViewHolder {

        private final RecyclerView mRecyclerView;

        public PopularViewHolder(View view) {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.popular_recycler_view);
        }

        public void initViews(LinearLayoutManager lm) {
            mRecyclerView.setLayoutManager(lm);
        }

        public void scrollToPosition(int position) {
            mRecyclerView.scrollToPosition(position);
        }

        public void setAdapter(RecyclerView.Adapter<?> adapter) {
            mRecyclerView.setAdapter(adapter);
        }

        public void smoothScrollToPosition(int position) {
            mRecyclerView.smoothScrollToPosition(position);
        }
    }
}
