<?php

namespace App\Controller;

use App\Entity\OffreEmploi;
use App\Form\OffreEmploiType;
use App\Repository\OffreEmploiRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;


/**
 * @Route("/offre/emploi")
 */
class OffreEmploiController extends AbstractController
{
    /**
     * @Route("/", name="offre_emploi_index", methods={"GET"})
     */
    public function index(OffreEmploiRepository $offreEmploiRepository): Response
    {
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/2", name="offre_emploi_index2", methods={"GET"})
     */
    public function indexA(OffreEmploiRepository $offreEmploiRepository,FlashyNotifier $flashy): Response
    {
        $flashy->success('ajout effectuer avec succeés!', 'http://your-awesome-link.com');
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/3", name="offre_emploi_index3", methods={"GET"})
     */
    public function indexB(OffreEmploiRepository $offreEmploiRepository,FlashyNotifier $flashy): Response
    {
        $flashy->success('modification effectuer avec succeés!', 'http://your-awesome-link.com');
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/4", name="offre_emploi_index4", methods={"GET"})
     */
    public function indexC(OffreEmploiRepository $offreEmploiRepository,FlashyNotifier $flashy): Response
    {
        $flashy->success('suppression effectuer avec succeés!', 'http://your-awesome-link.com');
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois' => $offreEmploiRepository->findAll(),

        ]);
    }
    /**
     * @Route("/Front", name="Front_index", methods={"GET"})
     */
    public function Frontindex2(OffreEmploiRepository $offreEmploiRepository): Response
    {
        return $this->render('/baseFront.html.twig', [
            'baseFront' => $offreEmploiRepository->findAll(),
        ]);
    }
    /**
     * @Route("/front2", name="Front_offre_emploi_index", methods={"GET"})
     */
    public function Frontindex(Request $request, PaginatorInterface $paginator): Response
    {
        $repository=$this->getdoctrine()->getRepository(OffreEmploi :: class)->findAll();
        $offre_emplois=$paginator->paginate(
            $repository,
            $request->query->getInt('page', 1),
            3
        );
        return $this->render('Front/offre_emplois.html.twig', [
            'offre_emplois' => $offre_emplois,
        ]);
    }


    /**
     * @Route("/new", name="offre_emploi_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $offreEmploi = new OffreEmploi();
        $form = $this->createForm(OffreEmploiType::class, $offreEmploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($offreEmploi);
            $entityManager->flush();

            return $this->redirectToRoute('offre_emploi_index2');
        }

        return $this->render('offre_emploi/new.html.twig', [
            'offre_emploi' => $offreEmploi,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/news", name="offre_emploi_news")
     */
    public function tri(OffreEmploiRepository $offreEmploiRepository)
    {
        $student=$this->getDoctrine()->getRepository(OffreEmploi::class)->findByASC();
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois' => $student,
        ]);
    }

    /**
     * @Route("/newss", name="offre_emploi_newss")
     */
    function chercher(OffreEmploiRepository $repository,Request $request)
    {
        $data=$request->get('AfficherClasse');
        $student=$repository->findBy(['entreprise'=>$data]);
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois'=>$student

            ]);
    }
    /**
     * @Route("/newsss", name="offre_emploi_newsss")
     */
    function recherche(OffreEmploiRepository $repository,Request $request)
    {
        $data=$request->get('search');
        $student=$repository->findBy(['Type'=>$data]);
        return $this->render('offre_emploi/index.html.twig', [
            'offre_emplois'=>$student
        ]);

    }

    /**
     * @Route("/stat", name="offre_emploi_stat")
     */
    public function statistiques(OffreEmploiRepository $OffreEmploiRepository)
    {
        $offreStage = $OffreEmploiRepository->findAll();
        $PFE=$this->getdoctrine()->getRepository(OffreEmploi :: class)->countForStage('saisonnier');
        $Tech=$this->getdoctrine()->getRepository(OffreEmploi :: class)->countForStage('de Vacances');
        $ouvrier=$this->getdoctrine()->getRepository(OffreEmploi :: class)->countForStage('solidaire');
        $TEST=$this->getdoctrine()->getRepository(OffreEmploi :: class)->countForStage('long terme');
        $arrayOFcolors=["red","blue","green"];
        $array=[$PFE,$Tech,$ouvrier,$TEST];
        $arrayOFnames=["saisonnier","Vacances","solidaire","long terme"];
        return $this->render('offre_emploi/stat.html.twig',[
            'array' => json_encode($array),
            'arrayOFnames' => json_encode($arrayOFnames),
            'arrayOFcolors' => json_encode($arrayOFcolors)
        ]);
    }


    /**
     * @Route("/{id}", name="offre_emploi_show", methods={"GET"})
     */
    public function show(OffreEmploi $offreEmploi): Response
    {
        return $this->render('offre_emploi/show.html.twig', [
            'offre_emploi' => $offreEmploi,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="offre_emploi_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, OffreEmploi $offreEmploi): Response
    {
        $form = $this->createForm(OffreEmploiType::class, $offreEmploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('offre_emploi_index3');
        }

        return $this->render('offre_emploi/edit.html.twig', [
            'offre_emploi' => $offreEmploi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="offre_emploi_delete", methods={"DELETE"})
     */
    public function delete(Request $request, OffreEmploi $offreEmploi): Response
    {
        if ($this->isCsrfTokenValid('delete'.$offreEmploi->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($offreEmploi);
            $entityManager->flush();
        }

        return $this->redirectToRoute('offre_emploi_index4');
    }


}
